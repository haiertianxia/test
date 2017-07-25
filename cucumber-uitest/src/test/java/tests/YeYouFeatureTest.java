package tests;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/resources/feature/yeyou"},
        format = {"pretty", "html:target/yeyou/cucumber", "json:target/yeyou/cucumber.json"},
        glue = {"stepDefintions"}
//       ,tags = {"~@undefined"}默认运行全部
     	//,tags = {"@yeyou"}        	
             
)
public class YeYouFeatureTest {
}
