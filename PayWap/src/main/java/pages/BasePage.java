package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * Created by admin on 2017/3/17.
 * 暂时不用
 */
public class BasePage {
    public WebDriver getWebDriver(String browser) {

        WebDriver driver = null;

        if ("ie".equals(browser)) {

            //  ie

            System.setProperty("webdriver.ie.driver", "C:/Program Files (x86/2345Soft/2345Explorer/2345Explorer.exe");

            DesiredCapabilities capab = DesiredCapabilities.internetExplorer();

            capab.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);

            capab.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);

            driver = new InternetExplorerDriver(capab);

        } else if ("chrome".equals(browser)) {

            //chrome

            System.setProperty("webdriver.chrome.driver", "C:/Users/admin/AppData/Local/Google/Chrome/Application/chrome.exe");

            driver = new ChromeDriver();

        } else {

            //firefox

            System.setProperty("webdriver.firefox.bin", "D:/Program Files/Mozilla Firefox/firefox.exe");

            System.setProperty("webdriver.gecko.driver", "D:/Program Files/WebDriver/geckodriver.exe");

            driver = new FirefoxDriver();

        }

        driver.manage().window().maximize();

        return driver;

    }

}
