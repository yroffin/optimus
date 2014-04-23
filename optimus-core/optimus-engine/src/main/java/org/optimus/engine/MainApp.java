package org.optimus.engine;

import org.optimus.application.OptimusApp;
import org.optimus.application.impl.OptimusAppImpl;
import org.optimus.engine.business.impl.OptimusBusinessImpl;
import org.optimus.nosql.impl.NoSqlDriverException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainApp {
	final static Logger logger = LoggerFactory.getLogger(MainApp.class);

	public static void main(String[] args) {
	}

	public static void execute() throws NoSqlDriverException {
		logger.info("Execute");
		OptimusApp app = new OptimusAppImpl();
		app.setOptimusBusiness(new OptimusBusinessImpl());
		app.init();
		app.execute();
	}
}
