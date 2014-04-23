package org.optimus.application;

import org.optimus.engine.business.impl.OptimusBusinessImpl;
import org.optimus.nosql.impl.NoSqlDriverException;

public interface OptimusApp {

	void setOptimusBusiness(OptimusBusinessImpl optimusBusinessIml);

	void init() throws NoSqlDriverException;

	void execute();

}
