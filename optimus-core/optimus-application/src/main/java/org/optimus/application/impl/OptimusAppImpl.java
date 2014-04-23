package org.optimus.application.impl;

import java.util.ArrayList;
import java.util.List;

import org.optimus.application.OptimusApp;
import org.optimus.connector.impl.SourceConnectorImpl;
import org.optimus.engine.business.impl.OptimusBusinessImpl;
import org.optimus.job.impl.DefaultJobImpl;
import org.optimus.model.event.GenericEvent;
import org.optimus.nosql.NoSqlConn;
import org.optimus.nosql.NoSqlDriver;
import org.optimus.nosql.NoSqlDriverHelper;
import org.optimus.nosql.impl.NoSqlDriverException;
import org.optimus.repository.utils.RepositoryUtils;
import org.optimus.repository.utils.impl.NoSqlRepositoryUtilsImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.BasicDBList;

public class OptimusAppImpl implements OptimusApp {
	final static Logger logger = LoggerFactory.getLogger(OptimusAppImpl.class);

	private OptimusBusinessImpl optimusBusiness;

	public void setOptimusBusiness(OptimusBusinessImpl optimusBusinessImpl) {
		this.optimusBusiness = optimusBusinessImpl;
	}

	private GenericEvent configuration;
	RepositoryUtils repositoryUtils = new NoSqlRepositoryUtilsImpl();

	private List<DefaultJobImpl> jobs = new ArrayList<DefaultJobImpl>();

	private NoSqlDriver driver;

	private NoSqlConn conn;

	public void init() throws NoSqlDriverException {
		logger.info("Init");
		optimusBusiness.init();
		driver = NoSqlDriverHelper.getDriver();
		conn = driver.getConn("configuration");
		// get jobs repository
		for (GenericEvent job : repositoryUtils.findCollection(conn, "jobs")) {
			DefaultJobImpl e = new DefaultJobImpl((String) job.get("name"));
			BasicDBList connectors = (BasicDBList) job.get("connectors");
			// add connectors
			for (Object connector : connectors.toArray()) {

			}
			e.add(new SourceConnectorImpl("c:/temp/test", false));
			jobs.add(e);
		}
	}

	public void execute() {
		logger.info("Execute jobs");
		for (DefaultJobImpl job : jobs) {
			logger.info("Execute business job {}", job);
			optimusBusiness.execute(job);
		}
	}
}
