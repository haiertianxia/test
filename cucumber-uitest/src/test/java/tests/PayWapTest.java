package tests;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)@CucumberOptions(
        features = {"src/test/resources/feature/paywap"},
        format = {"pretty", "html:target/paywap/cucumber", "json:target/paywap/cucumber.json"},
        glue = {"stepDefintions"}
       ,tags = {"~@undefined"}//默认运行全部
    	//,tags = {"@mycount"}
       // ,tags = {"@baijin"} 
       // ,tags = {"@superVIP"}
       // ,tags = {"@game"}
       // ,tags = {"@highGame"}
        //,tags = {"@quickBird"} 
        //,tags = {"@pointPay"} 
        //,tags = {"@pointRecharge"} 
             )
public class PayWapTest {
}
