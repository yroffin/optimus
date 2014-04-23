package org.optimus.nosql.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.bson.BSONObject;
import org.optimus.model.configuration.GenericMap;
import org.optimus.nosql.NoSqlConn;

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

	public GenericMap fromBSONObject(GenericMap accu, BSONObject obj) {
		for(String key : obj.keySet()) {
			obj.get(key);
		}
		return accu;
	}

	public List<GenericMap> getCollection(String name) {
		DBCollection coll = db.getCollection(name);

		List<GenericMap> objects = new ArrayList<GenericMap>();
		DBCursor cursor = coll.find();
		try {
		   while(cursor.hasNext()) {
		       DBObject obj = cursor.next();
		       objects.add(new GenericMap(obj.toMap()));
		   }
		} finally {
		   cursor.close();
		}

		return objects;
	}

}
