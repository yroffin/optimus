package org.optimus.job.impl;

import org.optimus.job.AbstractJob;
import org.optimus.model.job.JobBean;

public class DefaultJobImpl extends AbstractJob {

	private JobBean job = new JobBean();

	public DefaultJobImpl(String name) {
		job.setName(name);
	}

	public String getName() {
		return job.getName();
	}

	@Override
	public String toString() {
		return "DefaultJob [name=" + job + "]";
	}
}
