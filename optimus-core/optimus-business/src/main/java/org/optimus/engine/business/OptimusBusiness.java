package org.optimus.engine.business;

import org.optimus.model.configuration.GenericMap;
import org.optimus.model.job.DefaultJob;

public interface OptimusBusiness {
	public void execute(DefaultJob job);
}
