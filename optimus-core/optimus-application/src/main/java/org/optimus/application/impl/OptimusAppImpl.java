package org.optimus.application.impl;

import java.util.ArrayList;
import java.util.List;

import org.optimus.application.OptimusApp;
import org.optimus.engine.business.impl.OptimusBusinessImpl;
import org.optimus.model.configuration.GenericMap;
import org.optimus.model.job.DefaultJob;
import org.optimus.nosql.NoSqlConn;
import org.optimus.nosql.NoSqlDriver;
import org.optimus.nosql.NoSqlDriverHelper;
import org.optimus.nosql.impl.NoSqlDriverException;
import org.optimus.repository.utils.RepositoryUtils;
import org.optimus.repository.utils.impl.NoSqlRepositoryUtilsImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OptimusAppImpl implements OptimusApp {
	final static Logger logger = LoggerFactory.getLogger(OptimusAppImpl.class);

	private OptimusBusinessImpl optimusBusiness;

	public void setOptimusBusiness(OptimusBusinessImpl optimusBusinessImpl) {
		this.optimusBusiness = optimusBusinessImpl;		
	}

	private GenericMap configuration;
	RepositoryUtils repositoryUtils = new NoSqlRepositoryUtilsImpl();

	private List<DefaultJob> jobs = new ArrayList<DefaultJob>();

	private NoSqlDriver driver;

	private NoSqlConn conn;

	public void init() throws NoSqlDriverException {
		logger.info("Init");
		optimusBusiness.init();
		driver = NoSqlDriverHelper.getDriver();
		conn = driver.getConn("configuration");
		/**
		 * get jobs repository
		 */
		for(GenericMap job : repositoryUtils.findCollection(conn, "jobs")) {
			DefaultJob e = new DefaultJob((String) job.get("name"));
			
			e.add(new ConnectorImpl());
			jobs.add(e );
		}
	}

	public void execute() {
		logger.info("Execute jobs");
		for(DefaultJob job : jobs) {
			logger.info("Execute jobs {}", job);
			optimusBusiness.execute(job);
		}
	}
}
