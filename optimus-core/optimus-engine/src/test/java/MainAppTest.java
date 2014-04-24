import org.junit.Test;
import org.optimus.engine.MainApp;
import org.optimus.exception.FunctionnalException;
import org.optimus.exception.TechnicalException;

public class MainAppTest {

	@Test
	public void test() throws TechnicalException, FunctionnalException {
		MainApp.execute();
	}

}
