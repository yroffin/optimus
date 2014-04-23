package org.optimus.nosql;

import org.optimus.nosql.impl.MongoDbDriver;
import org.optimus.nosql.impl.NoSqlDriverException;

public class NoSqlDriverHelper {
	public static NoSqlDriver getDriver() throws NoSqlDriverException {
		return new MongoDbDriver("localhost" , 27017);
	}
}
