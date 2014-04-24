package org.optimus.model.event;

import java.util.HashMap;
import java.util.Map;

public class GenericEvent {
	Map<String, Object> members = new HashMap<String, Object>();

	public GenericEvent(Map map) {
		members = map;
	}

	public Object get(String key) {
		return members.get(key);
	}

	public void put(String key, Object value) {
		members.put(key, value);
	}

	@Override
	public String toString() {
		return "GenericEvent [members=" + members + "]";
	}
}
