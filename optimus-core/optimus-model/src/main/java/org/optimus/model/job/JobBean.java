package org.optimus.model.job;

public class JobBean {
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "JobBean [name=" + name + "]";
	}
}
