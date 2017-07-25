package tests;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/resources/feature/wapaqcenter"},
        format = {"pretty", "html:target/wapaqcenter/cucumber", "json:target/wapaqcenter/cucumber.json"},
        glue = {"stepDefintions"}
//     	,tags = {"@findPwd"}        	
)
public class WapAQCenterFeatureTest {
}
