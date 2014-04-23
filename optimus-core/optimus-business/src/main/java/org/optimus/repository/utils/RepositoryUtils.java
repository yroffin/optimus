package org.optimus.repository.utils;

import java.util.List;
import java.util.Set;

import org.optimus.model.event.GenericEvent;
import org.optimus.nosql.NoSqlConn;

public interface RepositoryUtils {
	public Set<String> collections(NoSqlConn db);

	public List<GenericEvent> findCollection(NoSqlConn db, String name);
}
