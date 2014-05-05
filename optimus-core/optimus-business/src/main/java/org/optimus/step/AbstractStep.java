package org.optimus.step;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.optimus.exception.FunctionnalException;
import org.optimus.exception.TechnicalException;
import org.optimus.model.event.GenericEvent;
import org.optimus.step.impl.SourceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractStep implements Step {
	final static Logger logger = LoggerFactory.getLogger(SourceImpl.class);

	public void execute(List<GenericEvent> in, List<GenericEvent> out) throws TechnicalException, FunctionnalException {
		throw new TechnicalException("Must be overriden !!!");
	}

	private boolean persistent = true;
	private List<String> inputs = new ArrayList<String>();
	private List<String> outputs = new ArrayList<String>();

	public void init(Map map) {
		List list = null;
		list = (List) map.get("outputs");
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				outputs.add((String) list.get(i));
			}
		}
		list = (List) map.get("inputs");
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				inputs.add((String) list.get(i));
			}
		}
	}

	public void execute(Map<String, List<GenericEvent>> in, Map<String, List<GenericEvent>> out) throws TechnicalException, FunctionnalException {
		if (in.size() == 0) {
			logger.info("Processing no input");
			for (String keyOut : out.keySet()) {
				logger.info("Processing output {}", keyOut);
				execute(null, out.get(keyOut));
			}
		} else {
			for (String keyIn : in.keySet()) {
				logger.info("Processing input {}", keyIn);
				for (String keyOut : out.keySet()) {
					logger.info("Processing output {}", keyOut);
					execute(in.get(keyIn), out.get(keyOut));
				}
			}
		}
	}

	public List<String> getOutputs() {
		return outputs;
	}

	public List<String> getInputs() {
		return inputs;
	}

	public boolean isPersistent() {
		return persistent;
	}

	public void setPersistent(boolean persistent) {
		this.persistent = persistent;
	}

	@Override
	public String toString() {
		return "AbstractStep [inputs=" + inputs + ", outputs=" + outputs + "]";
	}
}
