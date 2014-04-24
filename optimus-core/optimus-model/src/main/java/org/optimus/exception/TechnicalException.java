package org.optimus.exception;

import java.io.IOException;
import java.sql.SQLException;

public class TechnicalException extends Exception {

	public TechnicalException(String message) {
		super(message);
	}

	public TechnicalException(RuntimeException e) {
		super(e);
	}

	public TechnicalException(IOException e) {
		super(e);
	}

	public TechnicalException(SQLException e) {
		super(e);
	}

	public TechnicalException(ClassNotFoundException e) {
		super(e);
	}

	public TechnicalException(InstantiationException e) {
		super(e);
	}

	public TechnicalException(IllegalAccessException e) {
		super(e);
	}
}
