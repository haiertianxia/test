package runner;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.log4testng.Logger;

/**
 * Created by admin on 2017/3/28.
 */

/**
 * 重跑
 */
public class TestngRetry implements IRetryAnalyzer {
    private static Logger logger = Logger.getLogger(TestngRetry.class);
    private int retryCount = 1;
    private static int maxRetryCount = 3;

    //  private static ConfigReader config;
    static {
        //外围文件配置最大运行次数

        logger.info("maxRunCount=" + (maxRetryCount));
    }

    @Override
    public boolean retry(ITestResult result) {
        if (retryCount <= maxRetryCount) {
            String message = "running retry for  '" + result.getName() + "' on class " +
                    this.getClass().getName() + " Retrying " + retryCount + " times";
            logger.info(message);
            Reporter.setCurrentTestResult(result);
            Reporter.log("RunCount=" + (retryCount + 1));
            retryCount++;
            return true;
        }
        return false;
    }

}

