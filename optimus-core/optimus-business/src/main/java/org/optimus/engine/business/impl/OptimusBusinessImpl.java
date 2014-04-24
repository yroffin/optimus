package org.optimus.engine.business.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.optimus.engine.business.OptimusBusiness;
import org.optimus.exception.FunctionnalException;
import org.optimus.exception.TechnicalException;
import org.optimus.job.Job;
import org.optimus.model.event.GenericEvent;
import org.optimus.nosql.NoSqlConn;
import org.optimus.repository.utils.RepositoryUtils;
import org.optimus.step.Step;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OptimusBusinessImpl implements OptimusBusiness {
	final static Logger logger = LoggerFactory.getLogger(OptimusBusinessImpl.class);

	public void init() {
		logger.info("Init");
	}

	public void execute(Job job, RepositoryUtils repository, NoSqlConn conn) throws TechnicalException, FunctionnalException {
		logger.info("Execute {} with {} step(s)", job, job.getSteps().size());
		// Iterate on each step
		for (Step step : job.getSteps()) {
			// Based on step declaration find all collections in current
			// context
			Map<String, List<GenericEvent>> in = findInputs(step, repository, conn);
			Map<String, List<GenericEvent>> out = findOutputs(step, repository, conn);
			logger.info("Execute step {} with {} input collection(s) and {} output collection(s)", step, in.size(), out.size());
			try {
				execute(step, in, out);
			} catch (TechnicalException e) {
				// Abort processing for technical reasons
				throw e;
			} catch (FunctionnalException e) {
				// Abort processing for functionnal reasons
				throw e;
			} catch (RuntimeException e) {
				// Abort processing for runtime reasons
				throw new TechnicalException(e);
			}
			for (String key : out.keySet()) {
				logger.info("Execute step {} produce {} event(s) on {} collection", step, out.get(key).size(), key);
			}
		}
	}

	public void execute(Step step, Map<String, List<GenericEvent>> in, Map<String, List<GenericEvent>> out) throws TechnicalException,
			FunctionnalException {
		logger.info("Execute step {}", step);
		step.execute(in, out);
		logger.info("Execute step {} done", step);
	}

	/**
	 * acquire all inputs from NoSql db
	 * 
	 * @param step
	 * @param repository
	 * @param conn
	 * @return
	 */
	private Map<String, List<GenericEvent>> findInputs(Step step, RepositoryUtils repository, NoSqlConn conn) {
		Map<String, List<GenericEvent>> inputs = new HashMap<String, List<GenericEvent>>();
		for (String input : step.getInputs()) {
			List<GenericEvent> collection = repository.findAll(conn, input);
			inputs.put(input, collection);
		}
		return inputs;
	}

	/**
	 * reset all output collections
	 * 
	 * @param step
	 * @param repository
	 * @param conn
	 * @return
	 */
	private Map<String, List<GenericEvent>> findOutputs(Step step, RepositoryUtils repository, NoSqlConn conn) {
		Map<String, List<GenericEvent>> outputs = new HashMap<String, List<GenericEvent>>();
		for (String output : step.getOutputs()) {
			repository.reset(conn, output);
			outputs.put(output, new ArrayList<GenericEvent>());
		}
		return outputs;
	}

}
