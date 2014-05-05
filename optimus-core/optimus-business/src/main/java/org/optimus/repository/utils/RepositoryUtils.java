package org.optimus.repository.utils;

import java.util.List;
import java.util.Set;

import org.optimus.exception.TechnicalException;
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
	 * @throws TechnicalException
	 */
	public List<GenericEvent> findAll(NoSqlConn db, String name) throws TechnicalException;

	/**
	 * reset this collection
	 * 
	 * @param conn
	 * @param output
	 */
	public void reset(NoSqlConn db, String name);

	/**
	 * store collection
	 * 
	 * @param list
	 * @param conn
	 * @throws TechnicalException
	 */
	public void store(NoSqlConn db, String name, List<GenericEvent> list) throws TechnicalException;
}
