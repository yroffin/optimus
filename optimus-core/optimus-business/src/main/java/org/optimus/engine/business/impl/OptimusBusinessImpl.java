package org.optimus.engine.business.impl;

import java.util.ArrayList;
import java.util.List;

import org.optimus.connector.Connector;
import org.optimus.engine.business.OptimusBusiness;
import org.optimus.job.Job;
import org.optimus.model.event.GenericEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OptimusBusinessImpl implements OptimusBusiness {
	final static Logger logger = LoggerFactory.getLogger(OptimusBusinessImpl.class);

	public void init() {
		logger.info("Init");
	}

	public void execute(Job job) {
		logger.info("Execute {} with {} connector(s)", job, job.getConnectors().size());
		List<GenericEvent> events = new ArrayList<GenericEvent>();
		for (Connector connector : job.getConnectors()) {
			logger.info("Execute job {} on {} event(s)", connector, events.size());
			events = execute(connector, events);
		}
	}

	public List<GenericEvent> execute(Connector connector, List<GenericEvent> events) {
		logger.info("Execute {}", connector);
		return connector.execute(events);
	}

}
