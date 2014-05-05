package org.optimus.step.impl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.optimus.exception.FunctionnalException;
import org.optimus.exception.TechnicalException;
import org.optimus.model.event.GenericEvent;
import org.optimus.step.AbstractStep;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SourceCaptureImpl extends AbstractStep {
	final static Logger logger = LoggerFactory.getLogger(SourceCaptureImpl.class);
	private String content;

	@Override
	public void init(Map map) {
		super.init(map);
		this.content = (String) map.get("content");
		if (this.content == null) {
			this.content = "";
		}
	}

	private String readFile(String file) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String line = null;
		StringBuilder stringBuilder = new StringBuilder();
		String ls = System.getProperty("line.separator");

		while ((line = reader.readLine()) != null) {
			stringBuilder.append(line);
		}

		return stringBuilder.toString();
	}

	@Override
	public void execute(List<GenericEvent> in, List<GenericEvent> out) throws TechnicalException, FunctionnalException {
		try {
			for (GenericEvent event : in) {
				String fullName = (String) event.get("file");
				this.content = readFile(fullName);
				out.add(new GenericEvent("{\"content\":\"" + content + "\"}"));
			}
		} catch (IOException e) {
			throw new TechnicalException(e);
		}
	}

	@Override
	public String toString() {
		return "SourceCaptureImpl [content=" + content.length() + "]";
	}
}
