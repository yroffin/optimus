package org.optimus.model.event;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.optimus.exception.TechnicalException;

public class GenericEvent {

	static ObjectMapper mapper = new ObjectMapper();

	Map<String, Object> members = new HashMap<String, Object>();

	@SuppressWarnings("unchecked")
	public GenericEvent(String json) throws TechnicalException {
		try {
			members = mapper.readValue(json, Map.class);
		} catch (JsonParseException e) {
			throw new TechnicalException(e);
		} catch (JsonMappingException e) {
			throw new TechnicalException(e);
		} catch (IOException e) {
			throw new TechnicalException(e);
		}
	}

	public Object get(String key) {
		return members.get(key);
	}

	public void put(String key, Object value) {
		members.put(key, value);
	}

	public Map toMap() {
		return members;
	}
}
