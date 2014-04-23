import static org.junit.Assert.*;

import org.junit.Test;
import org.optimus.engine.MainApp;
import org.optimus.nosql.impl.NoSqlDriverException;


public class MainAppTest {

	@Test
	public void test() throws NoSqlDriverException {
		MainApp.execute();
	}

}
