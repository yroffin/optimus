package org.optimus.repository.utils.impl;

import java.util.List;
import java.util.Set;

import org.optimus.model.event.GenericEvent;
import org.optimus.nosql.NoSqlConn;
import org.optimus.repository.utils.RepositoryUtils;

public class NoSqlRepositoryUtilsImpl implements RepositoryUtils {
	public Set<String> collections(NoSqlConn db) {
		return db.getCollectionNames();
	}

	public List<GenericEvent> findCollection(NoSqlConn db, String name) {
		return db.getCollection(name);
	}
}
