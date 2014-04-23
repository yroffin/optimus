package org.optimus.nosql;

import java.util.List;
import java.util.Set;

import org.optimus.model.event.GenericEvent;

public interface NoSqlConn {

	Set<String> getCollectionNames();

	List<GenericEvent> getCollection(String name);

}
