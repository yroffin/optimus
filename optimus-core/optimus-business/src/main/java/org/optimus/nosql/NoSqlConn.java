package org.optimus.nosql;

import java.util.List;
import java.util.Set;

import org.optimus.model.configuration.GenericMap;

public interface NoSqlConn {

	Set<String> getCollectionNames();

	List<GenericMap> getCollection(String name);

}
