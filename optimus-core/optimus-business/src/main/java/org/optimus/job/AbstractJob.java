package org.optimus.job;

import java.util.ArrayList;
import java.util.List;

import org.optimus.connector.Connector;

public abstract class AbstractJob implements Job {
	protected List<Connector> connectors = new ArrayList<Connector>();

	public void add(Connector e) {
		connectors.add(e);
	}

	public List<Connector> getConnectors() {
		return connectors;
	}

	public void setConnectors(List<Connector> connectors) {
		this.connectors = connectors;
	}
}
