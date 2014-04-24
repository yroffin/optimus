package org.optimus.job;

import java.util.List;

import org.optimus.step.Step;

public interface Job {
	/**
	 * job name
	 * 
	 * @return
	 */
	public Object getName();

	/**
	 * get steps
	 * 
	 * @return
	 */
	public List<Step> getSteps();

	/**
	 * add new step
	 * 
	 * @param step
	 */
	public void add(Step step);
}
