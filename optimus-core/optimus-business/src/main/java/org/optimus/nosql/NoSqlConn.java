package org.optimus.nosql;

import java.util.List;
import java.util.Set;

import org.optimus.model.event.GenericEvent;

public interface NoSqlConn {

	/**
	 * get all collection in database
	 * 
	 * @return
	 */
	Set<String> getCollectionNames();

	/**
	 * find all elements
	 * 
	 * @param name
	 * @return
	 */
	List<GenericEvent> findAll(String name);

	/**
	 * reset this collection
	 * 
	 * @param name
	 */
	void reset(String name);

	/**
	 * store on db
	 * 
	 * @param list
	 * @param db
	 */
	void store(String name, List<GenericEvent> list);

}
