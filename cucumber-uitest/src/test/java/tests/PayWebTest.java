package tests;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)@CucumberOptions(
        features = {"src/test/resources/feature/payweb"},
        format = {"pretty", "html:target/payweb/cucumber", "json:target/payweb/cucumber.json"},
        glue = {"stepDefintions"}
       ,tags = {"~@undefined"}//默认运行全部
             )
public class PayWebTest {
}
