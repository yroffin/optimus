package org.optimus.nosql.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bson.BSONObject;
import org.optimus.exception.TechnicalException;
import org.optimus.model.event.GenericEvent;
import org.optimus.nosql.NoSqlConn;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

public class MongoDbConn implements NoSqlConn {

	private DB db;

	public MongoDbConn(DB db) {
		this.db = db;
	}

	public Set<String> getCollectionNames() {
		return db.getCollectionNames();
	}

	public GenericEvent fromBSONObject(GenericEvent accu, BSONObject obj) {
		for (String key : obj.keySet()) {
			obj.get(key);
		}
		return accu;
	}

	public List<GenericEvent> findAll(String name) throws TechnicalException {
		DBCollection coll = db.getCollection(name);

		List<GenericEvent> objects = new ArrayList<GenericEvent>();
		DBCursor cursor = coll.find();
		try {
			while (cursor.hasNext()) {
				DBObject obj = cursor.next();
				GenericEvent element = new GenericEvent(obj.toString());
				objects.add(element);
			}
		} finally {
			cursor.close();
		}

		return objects;
	}

	public void reset(String name) {
		db.getCollection(name).drop();
	}

	public void store(String name, List<GenericEvent> list) throws TechnicalException {
		DBCollection collection = db.getCollection(name);
		for (GenericEvent event : list) {
			DBObject dbo = (DBObject) buildDBObject(event.toMap());
			collection.insert(dbo);
		}
	}

	private DBObject buildFromMap(BasicDBObject builder, Map iterate) throws TechnicalException {
		for (Object key : iterate.keySet()) {
			Object value = buildDBObject(iterate.get(key));
			builder.put((String) key, value);
		}
		return builder;
	}

	private DBObject buildFromList(BasicDBList builder, List iterate) throws TechnicalException {
		for (Object item : iterate) {
			builder.add(buildDBObject(item));
		}
		return builder;
	}

	private Object buildDBObject(Object value) throws TechnicalException {
		// build Map
		if (value.getClass().getSimpleName().endsWith("Map")) {
			return buildFromMap(new BasicDBObject(), (Map) value);
		}
		// build a List
		if (value.getClass().getSimpleName().endsWith("List")) {
			return buildFromList(new BasicDBList(), (List) value);
		}
		// Native
		return value;
	}
}
