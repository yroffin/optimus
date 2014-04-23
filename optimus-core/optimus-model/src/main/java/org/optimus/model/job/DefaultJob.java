package org.optimus.model.job;

public class DefaultJob extends AbstractJob {

	private String name;
	
	public DefaultJob(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "DefaultJob [name=" + name + "]";
	}

}
