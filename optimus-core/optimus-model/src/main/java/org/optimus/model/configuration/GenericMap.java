package org.optimus.model.configuration;

import java.util.HashMap;
import java.util.Map;

public class GenericMap {
	Map<String,Object> members = new HashMap<String,Object>();

	public GenericMap(Map map) {
		members = map;
	}

	public Object get(String key) {
		return members.get(key);
	}
}
