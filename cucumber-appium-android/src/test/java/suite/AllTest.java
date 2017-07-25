package suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

//import tests.CucumberReport;
import tests.FeatureTest;

@RunWith(Suite.class)
@SuiteClasses({FeatureTest.class })
public class AllTest {
	
}
