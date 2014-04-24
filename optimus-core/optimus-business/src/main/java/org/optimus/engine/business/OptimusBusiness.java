package org.optimus.engine.business;

import java.util.List;
import java.util.Map;

import org.optimus.exception.FunctionnalException;
import org.optimus.exception.TechnicalException;
import org.optimus.job.Job;
import org.optimus.model.event.GenericEvent;
import org.optimus.nosql.NoSqlConn;
import org.optimus.repository.utils.RepositoryUtils;
import org.optimus.step.Step;

public interface OptimusBusiness {
	/**
	 * process one job
	 * 
	 * @param job
	 * @param conn
	 * @param repository
	 * @throws TechnicalException
	 * @throws FunctionnalException
	 */
	public void execute(Job job, RepositoryUtils repository, NoSqlConn conn) throws TechnicalException, FunctionnalException;

	/**
	 * process step
	 * 
	 * @param step
	 * @param in
	 * @param out
	 * @return
	 * @throws TechnicalException
	 * @throws FunctionnalException
	 */
	public void execute(Step step, Map<String, List<GenericEvent>> in, Map<String, List<GenericEvent>> out) throws TechnicalException,
			FunctionnalException;

	/**
	 * init
	 */
	public void init();
}
