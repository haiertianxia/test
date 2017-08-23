package main;



import org.testng.TestNG;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2017/4/2.
 */
public class PayCommon {
    public static void main(String[] args) {

        TestNG test = new TestNG();
        List<String> suites = new ArrayList<String>();
        suites.add("testng.xml");
        test.setTestSuites(suites);
        test.run();
    }
}
