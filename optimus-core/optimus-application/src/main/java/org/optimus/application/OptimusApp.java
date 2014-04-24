package org.optimus.application;

import org.optimus.engine.business.impl.OptimusBusinessImpl;
import org.optimus.exception.FunctionnalException;
import org.optimus.exception.TechnicalException;

public interface OptimusApp {
	/**
	 * spring purpose
	 * 
	 * @param optimusBusinessIml
	 */
	void setOptimusBusiness(OptimusBusinessImpl optimusBusinessIml);

	/**
	 * init application
	 * 
	 * @throws TechnicalException
	 */
	void init() throws TechnicalException;

	/**
	 * execute it
	 * 
	 * @throws TechnicalException
	 * @throws FunctionnalException
	 */
	void execute() throws TechnicalException, FunctionnalException;
}
