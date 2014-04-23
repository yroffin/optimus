package org.optimus.nosql.impl;

import java.net.UnknownHostException;

import org.optimus.nosql.NoSqlConn;
import org.optimus.nosql.NoSqlDriver;

import com.mongodb.MongoClient;

public class MongoDbDriver implements NoSqlDriver {
	
	MongoClient mongoClient;

	public MongoDbDriver(String host, int port) throws NoSqlDriverException {
		try {
			mongoClient = new MongoClient( host, port );
		} catch (UnknownHostException e) {
			throw new NoSqlDriverException(e);
		}
	}

	public NoSqlConn getConn(String database) {
		return new MongoDbConn(mongoClient.getDB( database ));
	}
}
