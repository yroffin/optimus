package org.optimus.application.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.optimus.application.OptimusApp;
import org.optimus.engine.business.OptimusBusiness;
import org.optimus.engine.business.impl.OptimusBusinessImpl;
import org.optimus.exception.FunctionnalException;
import org.optimus.exception.TechnicalException;
import org.optimus.job.Job;
import org.optimus.job.impl.DefaultJobImpl;
import org.optimus.model.event.GenericEvent;
import org.optimus.nosql.NoSqlConn;
import org.optimus.nosql.NoSqlDriver;
import org.optimus.nosql.NoSqlDriverHelper;
import org.optimus.nosql.impl.NoSqlDriverException;
import org.optimus.repository.utils.RepositoryUtils;
import org.optimus.repository.utils.impl.NoSqlRepositoryUtilsImpl;
import org.optimus.step.Step;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OptimusAppImpl implements OptimusApp {
	final static Logger logger = LoggerFactory.getLogger(OptimusAppImpl.class);

	private OptimusBusiness optimusBusiness;

	public void setOptimusBusiness(OptimusBusinessImpl optimusBusinessImpl) {
		this.optimusBusiness = optimusBusinessImpl;
	}

	private GenericEvent configuration;
	RepositoryUtils repository = new NoSqlRepositoryUtilsImpl();

	private List<Job> jobs = new ArrayList<Job>();

	private NoSqlDriver driver;

	public void init() throws TechnicalException {
		logger.info("Init");
		// Init business
		optimusBusiness.init();
		try {
			driver = NoSqlDriverHelper.getDriver();
		} catch (NoSqlDriverException e) {
			throw new TechnicalException(new SQLException(e));
		}
		// Allocate connection
		NoSqlConn conn = driver.getConn("configuration");
		// get jobs repository
		for (GenericEvent job : repository.findAll(conn, "jobs")) {
			logger.info("Discover job '{}'", job.get("name"));
			Job jobImpl = new DefaultJobImpl((String) job.get("name"));
			jobs.add(jobImpl);
			@SuppressWarnings("rawtypes")
			List steps = (List) job.get("steps");
			logger.info("Discover {} step(s) for job {}", steps.size(), job.get("name"));
			if (steps != null) {
				// add connectors
				Iterator<Object> it = steps.iterator();
				while (it.hasNext()) {
					Map obj = (Map) it.next();
					String klass = (String) obj.get("class");
					try {
						logger.info("Instanciate class {}", klass);
						Step newStep = (Step) Class.forName(klass).newInstance();
						logger.info("Init class {}", klass);
						newStep.init(obj);
						logger.info("Add class {} to job {}", klass, jobImpl.getName());
						jobImpl.add(newStep);
					} catch (ClassNotFoundException e) {
						throw new TechnicalException(e);
					} catch (InstantiationException e) {
						throw new TechnicalException(e);
					} catch (IllegalAccessException e) {
						throw new TechnicalException(e);
					}
				}
			}
		}
	}

	public void execute() throws TechnicalException, FunctionnalException {
		NoSqlConn conn = driver.getConn("application");

		logger.info("Running {} jobs", jobs.size());
		for (Job job : jobs) {
			logger.info("Execute business job '{}'", job.getName());
			optimusBusiness.execute(job, repository, conn);
			logger.info("Execute business job '{}' done", job.getName());
		}
		logger.info("Running {} jobs done", jobs.size());
	}
}
