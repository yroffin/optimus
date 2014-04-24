package org.optimus.nosql.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.bson.BSONObject;
import org.optimus.model.event.GenericEvent;
import org.optimus.nosql.NoSqlConn;

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

	public List<GenericEvent> findAll(String name) {
		DBCollection coll = db.getCollection(name);

		List<GenericEvent> objects = new ArrayList<GenericEvent>();
		DBCursor cursor = coll.find();
		try {
			while (cursor.hasNext()) {
				DBObject obj = cursor.next();
				objects.add(new GenericEvent(obj.toMap()));
			}
		} finally {
			cursor.close();
		}

		return objects;
	}

	public void reset(String name) {
		db.getCollection(name).drop();
	}

	public void store(String name, List<GenericEvent> list) {
		DBCollection collection = db.getCollection(name);
		DBObject arr;
		for (GenericEvent event : list) {
			arr = new BasicDBObject().append("string", event.toString());
			collection.insert(arr);
		}
	}

}
