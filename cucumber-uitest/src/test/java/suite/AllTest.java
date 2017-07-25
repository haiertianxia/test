package suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

//import tests.CucumberReport;
import tests.YeYouFeatureTest;
import tests.IFrameFeatureTest;
import tests.PayWapTest;
import tests.PayWebTest;
import tests.WapAQCenterFeatureTest;

@RunWith(Suite.class)
@SuiteClasses({IFrameFeatureTest.class,PayWapTest.class,PayWebTest.class,WapAQCenterFeatureTest.class,YeYouFeatureTest.class })
public class AllTest {
	
}
