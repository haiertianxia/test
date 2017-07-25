package stepDefintions;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import config.ConfigManager;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import methods.ClickElementsMethods;
import methods.InputMethods;
import methods.MiscMethods;

public class PredefinedStepDefinitions {

	private AppiumDriver<? extends MobileElement> driver;

	private MiscMethods miscmethodObj;
//
//	private NavigateMethods navigationObj;
//	private AssertionMethods assertionObj;
	private ClickElementsMethods clickObj;
//	private ConfigurationMethods configObj;
	private InputMethods inputObj;
//	private ProgressMethods progressObj;
//	private JavascriptHandlingMethods javascriptObj;
//	private ScreenShotMethods screenshotObj;
//	private CookieMethods cookieObj;
	
	@Before("~@undefined")
	public void beforeScenario() {
		try {
			ConfigManager config = new ConfigManager();

			// set up appium
			File classpathRoot = new File("src/test/resources/");
			File appDir = new File(classpathRoot, "/apps");
			File app = new File(appDir, config.get("application.path"));
			DesiredCapabilities capabilities = new DesiredCapabilities();

			capabilities.setCapability("deviceName", config.get("deviceName"));
			capabilities.setCapability("app", app.getAbsolutePath());
			capabilities.setCapability("appPackage", config.get("base.pkg"));
			// capabilities.setCapability("appActivity", ".XLKDemoActivity");
			capabilities.setCapability("appActivity", config.get("application.activity"));

			capabilities.setCapability("unicodeKeyboard", "True");
			capabilities.setCapability("resetKeyboard", "True");

			driver = new AndroidDriver<>(new URL(config.get("appium_server_url")), capabilities);
			
//			wait = new WebDriverWait(driver, Integer.parseInt(config.get("WAIT_ELEMENT_TO_LOAD")));
			this.miscmethodObj = new MiscMethods();
//			this.navigationObj = new NavigateMethods(driver, wait);
//			this.assertionObj = new AssertionMethods(driver, wait);
			this.clickObj = new ClickElementsMethods(driver);
//
//			this.configObj = new ConfigurationMethods(driver);
			this.inputObj = new InputMethods(driver);
//			this.progressObj = new ProgressMethods(driver, wait);
//			this.javascriptObj = new JavascriptHandlingMethods(driver);
//			this.screenshotObj = new ScreenShotMethods(driver);
//			this.cookieObj = new CookieMethods(driver);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

	}

	@After
	public void embedScreenshot(Scenario scenario) {
		if (scenario.isFailed()) {
			try {
				byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
				scenario.embed(screenshot, "image/png");

			} catch (WebDriverException somePlatformsDontSupportScreenshots) {
				System.err.println(somePlatformsDontSupportScreenshots.getMessage());
			}
		}

	}

	@After
	public void afterScenario() {
		driver.quit();
	}

	@Then("^I click on element having (.+) \"(.*?)\"$")
	public void i_click_on_element_having_name(String type, String accessName) throws Exception {
		miscmethodObj.validateLocator(type);
		clickObj.click(type, accessName);
//		driver.findElementByName(name).click();
	}

	@Then("^I clear input field having class \"([^\"]*)\" with number (\\d+)$")
	public void i_clear_input_field_having_class_with_number(String className, int index) {
		inputObj.clearTextByClassNameAndIndex(className, index);
//		
//		String using = conventClassName(className);
//		driver.findElementsByClassName(using).get(index).clear();
//		System.out.println("clear");
//		WebElement ele = driver.findElementsByClassName(using).get(index);
//		clearEleText(ele);

	}

	@Then("^I enter \"([^\"]*)\" into input field having class \"([^\"]*)\" with number (\\d+)$")
	public void i_enter_into_input_field_having_class_with_number(String text, String className, int index) {
		inputObj.enterTextByClassNameAndIndex(className, text, index);
//		String using = conventClassName(className);
//		driver.findElementsByClassName(using).get(index).sendKeys(text);
	}

	@Then("^I should see element having class \"([^\"]*)\" with number (\\d+) contains \"([^\"]*)\"$")
	public void i_should_see_element_having_class_with_number_contains(String className, int index,
			String containstext) {
		String using = conventClassName(className);
		String text = driver.findElementsByClassName(using).get(index).getText();
		System.out.println("text:" + text);

		assertTrue(text.contains(containstext));

	}

	@Then("^I should see elements having class \"([^\"]*)\" contains \"([^\"]*)\"$")
	public void i_should_see_elements_having_class_contains(String className, String containstext) {
		String using = conventClassName(className);

		List<? extends MobileElement> eles = driver.findElementsByClassName(using);
		assertTrue(isElesContains(eles, containstext));

	}

	public boolean isElesContains(List<? extends MobileElement> eles, String containstext) {
		boolean flag = false;
		for (MobileElement ele : eles) {
			System.out.println(ele.getText());
			if (ele.getText().contains(containstext)) {
				flag = true;
			}
		}

		return flag;
	}

	public String conventClassName(String className) {
		String using = "";
		if (className.equalsIgnoreCase("EditText")) {
			using = "android.widget.EditText";
		}
		if (className.equalsIgnoreCase("TextView")) {
			using = "android.widget.TextView";
		}
		return using;
	}

	public void sleep(int sencond) {
		try {
			Thread.sleep(1000 * sencond);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

//	/**
//	 * This method for delete text in textView or textEdit
//	 * 
//	 * @author Young
//	 * @param text
//	 */
//	@SuppressWarnings({ "unchecked" })
//	public void clearText(String text) {
//		((AndroidDriver<AndroidElement>) driver).pressKeyCode(123);
//		for (int i = 0; i < text.length(); i++) {
//			((AndroidDriver<AndroidElement>) driver).pressKeyCode(67);
//		}
//	}
//
//	public void clearEleText(WebElement ele) {
//		sleep(2);
//		ele.click();
//		String text = ele.getAttribute("text");
//		System.out.println(text);
//		System.out.println("text length:" + text.length());
//		sleep(2);
//		clearText(text);
//		// ele.clear();
//	}
}