package tests;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/resources/feature/iframe"},
        format = {"pretty", "html:target/iframe/cucumber", "json:target/iframe/cucumber.json"},
        glue = {"stepDefintions"}
//       ,tags = {"@loginByTianYi"}	
             
)
public class IFrameFeatureTest {
}
