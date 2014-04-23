package org.optimus.repository.utils;

import java.util.List;
import java.util.Set;

import org.optimus.model.configuration.GenericMap;
import org.optimus.nosql.NoSqlConn;

public interface RepositoryUtils {
	public Set<String> collections(NoSqlConn db);

	public List<GenericMap> findCollection(NoSqlConn db, String name);
}
