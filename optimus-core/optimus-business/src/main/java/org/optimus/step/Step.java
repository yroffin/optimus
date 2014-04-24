package org.optimus.step;

import java.util.List;
import java.util.Map;

import org.optimus.exception.FunctionnalException;
import org.optimus.exception.TechnicalException;
import org.optimus.model.event.GenericEvent;

public interface Step {
	/**
	 * constructor init delegate
	 * 
	 * @param map
	 */
	public void init(Map map);

	/**
	 * execute this step in current job, with an input event list and produce
	 * another list (may be the same)
	 * 
	 * @return
	 */
	public void execute(Map<String, List<GenericEvent>> in, Map<String, List<GenericEvent>> out) throws TechnicalException, FunctionnalException;

	/**
	 * process one input for one output
	 * 
	 * @param in
	 * @param out
	 * @throws TechnicalException
	 * @throws FunctionnalException
	 */
	public void execute(List<GenericEvent> in, List<GenericEvent> out) throws TechnicalException, FunctionnalException;

	/**
	 * find inputs
	 * 
	 * @return
	 */
	public List<String> getInputs();

	/**
	 * find outputs
	 * 
	 * @return
	 */
	public List<String> getOutputs();
}
