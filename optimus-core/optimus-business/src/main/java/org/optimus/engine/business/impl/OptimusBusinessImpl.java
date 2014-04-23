package org.optimus.engine.business.impl;

import org.optimus.engine.business.OptimusBusiness;
import org.optimus.model.job.DefaultJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OptimusBusinessImpl implements OptimusBusiness {
	final static Logger logger = LoggerFactory.getLogger(OptimusBusinessImpl.class);

	public void init() {
		logger.info("Init");
	}

	public void execute(DefaultJob job) {
		logger.info("Execute {}", job);
	}

}
