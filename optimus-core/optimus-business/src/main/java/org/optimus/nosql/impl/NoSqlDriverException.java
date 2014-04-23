package org.optimus.nosql.impl;

import java.net.UnknownHostException;

public class NoSqlDriverException extends Exception {

	public NoSqlDriverException(UnknownHostException e) {
		super(e);
	}

}
