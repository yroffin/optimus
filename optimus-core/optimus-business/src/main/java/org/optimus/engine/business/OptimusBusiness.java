package org.optimus.engine.business;

import java.util.List;

import org.optimus.connector.Connector;
import org.optimus.job.Job;
import org.optimus.model.event.GenericEvent;

public interface OptimusBusiness {
	public void execute(Job job);

	public List<GenericEvent> execute(Connector connector, List<GenericEvent> events);
}
