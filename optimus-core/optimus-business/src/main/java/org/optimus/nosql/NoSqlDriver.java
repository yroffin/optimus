package org.optimus.nosql;

public interface NoSqlDriver {

	NoSqlConn getConn(String database);

}
