package org.optimus.repository.utils;

import java.util.List;
import java.util.Set;

import org.optimus.model.event.GenericEvent;
import org.optimus.nosql.NoSqlConn;

public interface RepositoryUtils {
	/**
	 * retrieve all collections name
	 * 
	 * @param db
	 * @return
	 */
	public Set<String> collections(NoSqlConn db);

	/**
	 * retrieve (or init) a fresh collection
	 * 
	 * @param db
	 * @param name
	 * @return
	 */
	public List<GenericEvent> findAll(NoSqlConn db, String name);

	/**
	 * reset this collection
	 * 
	 * @param conn
	 * @param output
	 */
	public void reset(NoSqlConn conn, String output);
}
