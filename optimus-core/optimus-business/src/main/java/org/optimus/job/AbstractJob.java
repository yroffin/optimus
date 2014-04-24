package org.optimus.job;

import java.util.ArrayList;
import java.util.List;

import org.optimus.step.Step;

public abstract class AbstractJob implements Job {
	protected List<Step> steps = new ArrayList<Step>();

	public void add(Step e) {
		steps.add(e);
	}

	public List<Step> getSteps() {
		return steps;
	}

	public void setSteps(List<Step> steps) {
		this.steps = steps;
	}
}
