package stepDefintions;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import config.ConfigManager;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.ca.I;
import cucumber.api.java.en.Then;
import methods.AssertionMethods;
import methods.ClickElementsMethods;
import methods.ConfigurationMethods;
import methods.CookieMethods;
import methods.InputMethods;
import methods.JavascriptHandlingMethods;
import methods.MiscMethods;
import methods.NavigateMethods;
import methods.ProgressMethods;
import methods.ScreenShotMethods;
import methods.TestCaseFailed;
import utils.MySqlUtils;
import utils.RedisUtils;

public class PredefinedStepDefinitions {
	private WebDriver driver;
	private WebDriverWait wait;
	private MiscMethods miscmethodObj;

	private NavigateMethods navigationObj;
	private AssertionMethods assertionObj;
	private ClickElementsMethods clickObj;
	private ConfigurationMethods configObj;
	private InputMethods inputObj;
	private ProgressMethods progressObj;
	private JavascriptHandlingMethods javascriptObj;
	private ScreenShotMethods screenshotObj;
	private CookieMethods cookieObj;

	@Before("~@undefined")
	public void beforeScenario() {
		ConfigManager config = new ConfigManager();

		DesiredCapabilities browser = null;

		if ("local".equalsIgnoreCase(config.get("start_style"))) {
			System.out.println("Start style is local !");
			if ("firefox".equalsIgnoreCase(config.get("browser"))) {
				System.setProperty("webdriver.firefox.bin", "C:/Program Files (x86)/Mozilla Firefox/firefox.exe");

				// 使用本地本机其的profile
				ProfilesIni pi = new ProfilesIni();
				FirefoxProfile profile = pi.getProfile("default");

				// 自定义profile
				// FirefoxProfile profile = new FirefoxProfile();
				// myProfile.setPreference("network.automatic-ntlm-auth.trusted-uris",
				// "http://,https://");
				// myProfile.setPreference("network.automatic-ntlm-auth.allow-non-fqdn",
				// true);
				// myProfile.setPreference("network.negotiate-auth.delegation-uris",
				// "http://,https://");
				// myProfile.setPreference("network.negotiate-auth.trusted-uris",
				// "http://,https://");
				// myProfile.setPreference("network.http.phishy-userpass-length",
				// 255);
				// myProfile.setPreference("security.csp.enable", false);

				// 添加插件
				// File file = new File("files/firebug-2.0.7-fx.xpi");
				// FirefoxProfile profile = new FirefoxProfile();
				// try {
				// profile.addExtension(file);
				// } catch (IOException e) {
				// e.printStackTrace();
				// }
				// profile.setPreference("extensions.firebug.currentVersion",
				// "2.0.7");
				// //active firebug extensions
				// profile.setPreference("extensions.firebug.allPagesActivation",
				// "on");

				// 加载Profiles
				// profiles file
				// path，C:\Users\cloudchen\AppData\Local\Mozilla\Firefox\Profiles
				// FirefoxProfile profile = new FirefoxProfile(new
				// File("filePath");
				driver = new FirefoxDriver(profile);
			} else if ("ie".equalsIgnoreCase(config.get("browser"))) {
				System.setProperty("webdriver.ie.driver", config.get("driver_resources") + "\\IEDriverServer.exe");
				DesiredCapabilities dc = DesiredCapabilities.internetExplorer();
				dc.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
				// IE默认启动保护模式，要么手动在浏览器的设置中关闭保护模式，要么在代码中加上这一句，即可
				dc.setCapability("ignoreProtectedModeSettings", true);
				driver = new InternetExplorerDriver(dc);
			} else {
				/*System.setProperty("webdriver.chrome.driver", config.get("driver_resources"));
			    ChromeOptions options = new ChromeOptions();
			   // options.addArguments("user-data-dir=C:\\Selenium\\BrowserProfile");
			    
				

				// options.addExtensions(paths);
				String profile = config.get("chrome_user-data-dir");
				options.addArguments("user-data-dir=" + profile);
				options.addArguments("–start-maximized");
				driver = new ChromeDriver(options);
				//driver = new ChromeDriver();
*/				
				System.setProperty("webdriver.chrome.driver", config.get("driver_resources"));
				ChromeOptions options = new ChromeOptions();
				//options.addArguments("user-data-dir=C:\\Selenium\\BrowserProfile");
				options.addArguments("user-data-dir="+config.get("chrome_user-data-dir"));
				//options.addArguments("–first run");
			
				options.addArguments("–start-maximized");
				driver = new ChromeDriver(options);
				

			}
		} else if ("remote".equalsIgnoreCase(config.get("start_style"))) {

			System.out.println("Start style is remote !");

			if ("firefox".equalsIgnoreCase(config.get("browser"))) {
				// 使用本地本机其的profile
				ProfilesIni pi = new ProfilesIni();
				FirefoxProfile profile = pi.getProfile("default");

				browser = DesiredCapabilities.firefox();
				browser.setCapability(FirefoxDriver.PROFILE, profile);
			} else if ("ie".equalsIgnoreCase(config.get("browser"))) {
				browser = DesiredCapabilities.internetExplorer();
				browser.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
				// IE默认启动保护模式，要么手动在浏览器的设置中关闭保护模式，要么在代码中加上这一句，即可
				browser.setCapability("ignoreProtectedModeSettings", true);

			} else {
				browser = DesiredCapabilities.chrome();
				browser.setCapability("chrome.switches", Arrays.asList("--incognito"));
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--test-type");
				// options.addExtensions(paths);
				// options.addArguments("user-data-dir=C:/Users/xl/AppData/Local/Google/Chrome/User
				// Data");
				browser.setCapability(ChromeOptions.CAPABILITY, options);
				browser.setCapability(CapabilityType.PAGE_LOAD_STRATEGY, config.get("pageLoadStrategy"));
			}
			browser.setJavascriptEnabled(true);
			try {
				driver = new RemoteWebDriver(new URL(config.get("selenium_server_url")), browser);
			} catch (MalformedURLException exceptions) {

			}
		}
	
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.get("implicitlyWait")), TimeUnit.SECONDS);
		driver.manage().deleteAllCookies();

		
		wait = new WebDriverWait(driver, Integer.parseInt(config.get("WAIT_ELEMENT_TO_LOAD")));
		this.miscmethodObj = new MiscMethods();
		this.navigationObj = new NavigateMethods(driver, wait);
		this.assertionObj = new AssertionMethods(driver, wait);
		this.clickObj = new ClickElementsMethods(driver, wait);

		this.configObj = new ConfigurationMethods(driver);
		this.inputObj = new InputMethods(driver, wait);
		this.progressObj = new ProgressMethods(driver, wait);
		this.javascriptObj = new JavascriptHandlingMethods(driver);
		this.screenshotObj = new ScreenShotMethods(driver);
		this.cookieObj = new CookieMethods(driver);
		javascriptObj.executejs("localStorage.clear");
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

	// Navigation Steps

	// Step to navigate to specified URL
	@Then("^I navigate to \"([^\"]*)\"$")
	public void navigate_to(String link) {
		navigationObj.navigateTo(link);
	}

	// Step to navigate forward
	@Then("^I navigate forward")
	public void navigate_forward() {
		navigationObj.navigate("forward");
	}

	// Step to navigate backward
	@Then("^I navigate back")
	public void navigate_back() {
		navigationObj.navigate("back");
	}

	// steps to refresh page
	@Then("^I refresh page$")
	public void refresh_page() {
		driver.navigate().refresh();
	}

	// Switch between windows

	// Switch to new window
	@Then("^I switch to new window having title \"(.*?)\"$")
	public void switch_to_new_window(String windowTile) {
		navigationObj.switchToNewWindow(windowTile);
	}

	// Switch to old window
	@Then("^I switch to previous having title \"(.*?)\"$")
	public void switch_to_old_window(String windowTitle) {
		navigationObj.switchToOldWindow(windowTitle);
	}

	// Switch to new window by window title
	@Then("^I close to window having title \"(.*?)\"$")
	public void close_window_by_title(String windowTitle) throws Exception {
		navigationObj.closeWindowByTitle(windowTitle);
	}

	@Then("^I close to teny window having title \"(.*?)\",\"(.*?)\"$")
	public void close_teny_window_by_title(String windowTitle,String windowTitlePay) throws Exception {
		navigationObj.closeTenyWindowByTitle(windowTitle, windowTitlePay);
	}
	// Close new window
	@Then("^I close new window$")
	public void close_new_window() {
		navigationObj.closeNewWindow();
	}

	// Switch between frame

	// Step to switch to frame by web element
	@Then("^I switch to frame having (.+) \"(.*?)\"$")
	public void switch_frame_by_element(String method, String value) {
		navigationObj.switchFrame(method, value);
	}

	// step to switch to main content
	@Then("^I switch to main content$")
	public void switch_to_default_content() {
		navigationObj.switchToDefaultContent();
	}

	@Then("^I switch to alert accept$")
	public void switch_to_alert_accept() {
		navigationObj.switchToAlertAccept();
	}
	
	// To interact with browser

	// step to resize browser
	@Then("^I resize browser window size to width (\\d+) and height (\\d+)$")
	public void resize_browser(int width, int heigth) {
		navigationObj.resizeBrowser(width, heigth);
	}

	// step to maximize browser
	@Then("^I maximize browser window$")
	public void maximize_browser() {
		navigationObj.maximizeBrowser();
	}

	// Step to close the browser
	@Then("^I close browser$")
	public void close_browser() {
		navigationObj.closeDriver();
	}

	// zoom in/out page

	// steps to zoom in page
	@Then("^I zoom in page$")
	public void zoom_in() {
		navigationObj.zoomInOut("ADD");
	}

	// steps to zoom out page
	@Then("^I zoom out page$")
	public void zoom_out() {
		navigationObj.zoomInOut("SUBTRACT");
	}

	// zoom out webpage till necessary element displays

	// steps to zoom out till element displays
	@Then("^I zoom out page till I see element having (.+) \"(.*?)\"$")
	public void zoom_till_element_display(String type, String accessName) throws Exception {
		miscmethodObj.validateLocator(type);
		navigationObj.zoomInOutTillElementDisplay(type, "substract", accessName);
	}

	// reset webpage view use

	@Then("^I reset page view$")
	public void reset_page_zoom() {
		navigationObj.zoomInOut("reset");
	}

	// scroll webpage

	@Then("^I scroll to (top|end) of page$")
	public void scroll_page(String to) throws Exception {
		navigationObj.scrollPage(to);
	}

	// scroll webpage to specific element

	@Then("^I scroll to element having (.+) \"(.*?)\"$")
	public void scroll_to_element(String type, String accessName) throws Exception {
		miscmethodObj.validateLocator(type);
		navigationObj.scrollToElement(type, accessName);
	}

	// hover over element

	// Note: Doesn't work on Windows firefox
	@Then("^I hover over element having (.+) \"(.*?)\"$")
	public void hover_over_element(String type, String accessName) throws Exception {
		miscmethodObj.validateLocator(type);
		navigationObj.hoverOverElement(type, accessName);
	}

	// Assertion steps

	/**
	 * page title checking
	 * 
	 * @param present
	 * @param title
	 * @throws TestCaseFailed
	 */
	@Then("^I should\\s*((?:not)?)\\s+see page title as \"(.+)\"$")
	public void check_title(String present, String title) throws TestCaseFailed {
		// System.out.println("Present :" + present.isEmpty());
		assertionObj.checkTitle(title, present.isEmpty());
	}

	// Assertion steps

	/**
	 * page url checking
	 * 
	 * @param present
	 * @param url
	 * @throws TestCaseFailed
	 */
	@Then("^I should\\s*((?:not)?)\\s+see page url as \"(.+)\"$")
	public void check_url(String present, String url) throws TestCaseFailed {
		// System.out.println("Present :" + present.isEmpty());
		assertionObj.checkCurrentUrl(url, present.isEmpty());
	}

	// step to check element partial text
	@Then("^I should\\s*((?:not)?)\\s+see page title having partial text as \"(.*?)\"$")
	public void check_partial_text(String present, String partialTextTitle) throws TestCaseFailed {
		// System.out.println("Present :" + present.isEmpty());
		assertionObj.checkPartialTitle(partialTextTitle, present.isEmpty());
	}

	// step to check element text
	@Then("^element having (.+) \"([^\"]*)\" should\\s*((?:not)?)\\s+have text as \"(.*?)\"$")
	public void check_element_text(String type, String accessName, String present, String value) throws Exception {
		miscmethodObj.validateLocator(type);
		assertionObj.checkElementText(type, value, accessName, present.isEmpty());
	}

	// step to check element partial text
	@Then("^element having (.+) \"([^\"]*)\" should\\s*((?:not)?)\\s+have partial text as \"(.*?)\"$")
	public void check_element_partial_text(String type, String accessName, String present, String value)
			throws Exception {
		miscmethodObj.validateLocator(type);
		assertionObj.checkElementPartialText(type, value, accessName, present.isEmpty());
	}

	// step to check attribute value
	@Then("^element having (.+) \"([^\"]*)\" should\\s*((?:not)?)\\s+have attribute \"(.*?)\" with value \"(.*?)\"$")
	public void check_element_attribute(String type, String accessName, String present, String attrb, String value)
			throws Exception {
		miscmethodObj.validateLocator(type);
		assertionObj.checkElementAttribute(type, attrb, value, accessName, present.isEmpty());
	}

	// step to check element enabled or not
	@Then("^element having (.+) \"([^\"]*)\" should\\s*((?:not)?)\\s+be (enabled|disabled)$")
	public void check_element_enable(String type, String accessName, String present, String state) throws Exception {
		miscmethodObj.validateLocator(type);
		boolean flag = state.equals("enabled");
		if (!present.isEmpty()) {
			flag = !flag;
		}
		assertionObj.checkElementEnable(type, accessName, flag);
	}

	// step to check element present or not
	@Then("^element having (.+) \"(.*?)\" should\\s*((?:not)?)\\s+be present$")
	public void check_element_presence(String type, String accessName, String present) throws Exception {
		miscmethodObj.validateLocator(type);
		assertionObj.checkElementPresence(type, accessName, present.isEmpty());
	}

	// step to assert checkbox is checked or unchecked
	@Then("^checkbox having (.+) \"(.*?)\" should be (checked|unchecked)$")
	public void is_checkbox_checked(String type, String accessName, String state) throws Exception {
		miscmethodObj.validateLocator(type);
		boolean flag = state.equals("checked");
		assertionObj.isCheckboxChecked(type, accessName, flag);
	}

	// steps to assert radio button checked or unchecked
	@Then("^radio button having (.+) \"(.*?)\" should be (selected|unselected)$")
	public void is_radio_button_selected(String type, String accessName, String state) throws Exception {
		miscmethodObj.validateLocator(type);
		boolean flag = state.equals("selected");
		assertionObj.isRadioButtonSelected(type, accessName, flag);
	}

	// steps to assert option by text from radio button group
	// selected/unselected
	@Then("^option \"(.*?)\" by (.+) from radio button group having (.+) \"(.*?)\" should be (selected|unselected)$")
	public void is_option_from_radio_button_group_selected(String option, String attrb, String type, String accessName,
			String state) throws Exception {
		miscmethodObj.validateLocator(type);
		boolean flag = state.equals("selected");
		assertionObj.isOptionFromRadioButtonGroupSelected(type, attrb, option, accessName, flag);
	}

	// steps to check link presence
	@Then("^link having text \"(.*?)\" should\\s*((?:not)?)\\s+be present$")
	public void check_element_presence(String accessName, String present) throws TestCaseFailed, Exception {
		assertionObj.checkElementPresence("linkText", accessName, present.isEmpty());
	}

	// steps to check partail link presence
	@Then("^link having partial text \"(.*?)\" should\\s*((?:not)?)\\s+be present$")
	public void check_partial_element_presence(String accessName, String present) throws TestCaseFailed, Exception {
		assertionObj.checkElementPresence("partialLinkText", accessName, present.isEmpty());
	}

	// step to assert javascript pop-up alert text
	@Then("^I should see alert text as \"(.*?)\"$")
	public void check_alert_text(String actualValue) throws TestCaseFailed {
		assertionObj.checkAlertText(actualValue);
	}

	// step to select dropdown list
	@Then("^option \"(.*?)\" by (.+) from dropdown having (.+) \"(.*?)\" should be (selected|unselected)$")
	public void is_option_from_dropdown_selected(String option, String by, String type, String accessName, String state)
			throws Exception {
		miscmethodObj.validateLocator(type);
		boolean flag = state.equals("selected");
		assertionObj.isOptionFromDropdownSelected(type, by, option, accessName, flag);
	}

	// Input steps

	// enter text into input field steps
	@Then("^I enter \"([^\"]*)\" into input field having (.+) \"([^\"]*)\"$")
	public void enter_text(String text, String type, String accessName) throws Exception {
		miscmethodObj.validateLocator(type);
		inputObj.enterText(type, text, accessName);
	}

	@Then("^I get validate_code from \"([^\"]*)\" with account \"([^\"]*)\" and enter value into input field having (.+) \"([^\"]*)\"$")
	public void get_validate_code_enter_text(String dbName, String param, String type, String accessName)
			throws Throwable {
		String code = MySqlUtils.getValidateCode(dbName, param);
		miscmethodObj.validateLocator(type);
		inputObj.enterText(type, code, accessName);
	}

	// clear input field steps
	@Then("^I clear input field having (.+) \"([^\"]*)\"$")
	public void clear_text(String type, String accessName) throws Exception {
		miscmethodObj.validateLocator(type);
		inputObj.clearText(type, accessName);
	}

	// select option by text/value from dropdown
	@Then("^I select \"(.*?)\" option by (.+) from dropdown having (.+) \"(.*?)\"$")
	public void select_option_from_dropdown(String option, String optionBy, String type, String accessName)
			throws Exception {
		miscmethodObj.validateLocator(type);
		miscmethodObj.validateOptionBy(optionBy);
		inputObj.selectOptionFromDropdown(type, optionBy, option, accessName);
	}

	// select option by index from dropdown
	@Then("^I select (\\d+) option by index from dropdown having (.+) \"(.*?)\"$")
	public void select_option_from_dropdown_by_index(String option, String type, String accessName) throws Exception {
		miscmethodObj.validateLocator(type);
		inputObj.selectOptionFromDropdown(type, "selectByIndex", option, accessName);
	}

	// select option by text/value from multiselect
	@Then("^I select \"(.*?)\" option by (.+) from multiselect dropdown having (.+) \"(.*?)\"$")
	public void select_option_from_multiselect_dropdown(String option, String optionBy, String type, String accessName)
			throws Exception {
		miscmethodObj.validateLocator(type);
		miscmethodObj.validateOptionBy(optionBy);
		inputObj.selectOptionFromDropdown(type, optionBy, option, accessName);
	}

	// select option by index from multiselect
	@Then("^I select (\\d+) option by index from multiselect dropdown having (.+) \"(.*?)\"$")
	public void select_option_from_multiselect_dropdown_by_index(String option, String type, String accessName)
			throws Exception {
		miscmethodObj.validateLocator(type);
		inputObj.selectOptionFromDropdown(type, "selectByIndex", option, accessName);
	}

	// deselect option by text/value from multiselect
	@Then("^I deselect \"(.*?)\" option by (.+) from multiselect dropdown having (.+) \"(.*?)\"$")
	public void deselect_option_from_multiselect_dropdown(String option, String optionBy, String type,
			String accessName) throws Exception {
		miscmethodObj.validateLocator(type);
		miscmethodObj.validateOptionBy(optionBy);
		inputObj.deselectOptionFromDropdown(type, optionBy, option, accessName);
	}

	// deselect option by index from multiselect
	@Then("^I deselect (\\d+) option by index from multiselect dropdown having (.+) \"(.*?)\"$")
	public void deselect_option_from_multiselect_dropdown_by_index(String option, String type, String accessName)
			throws Exception {
		miscmethodObj.validateLocator(type);
		inputObj.deselectOptionFromDropdown(type, "selectByIndex", option, accessName);
	}

	// step to select option from mutliselect dropdown list
	/*
	 * @Then(
	 * "^I select all options from multiselect dropdown having (.+) \"(.*?)\"$")
	 * public void select_all_option_from_multiselect_dropdown(String
	 * type,String accessName) throws Exception {
	 * miscmethod.validateLocator(type); //inputObj.
	 * //select_all_option_from_multiselect_dropdown(type, access_name) }
	 */

	// step to unselect option from mutliselect dropdown list
	@Then("^I deselect all options from multiselect dropdown having (.+) \"(.*?)\"$")
	public void unselect_all_option_from_multiselect_dropdown(String type, String accessName) throws Exception {
		miscmethodObj.validateLocator(type);
		inputObj.unselectAllOptionFromMultiselectDropdown(type, accessName);
	}

	// check checkbox steps
	@Then("^I check the checkbox having (.+) \"(.*?)\"$")
	public void check_checkbox(String type, String accessName) throws Exception {
		miscmethodObj.validateLocator(type);
		inputObj.checkCheckbox(type, accessName);
	}

	// uncheck checkbox steps
	@Then("^I uncheck the checkbox having (.+) \"(.*?)\"$")
	public void uncheck_checkbox(String type, String accessName) throws Exception {
		miscmethodObj.validateLocator(type);
		inputObj.uncheckCheckbox(type, accessName);
	}

	// steps to toggle checkbox
	@Then("^I toggle checkbox having (.+) \"(.*?)\"$")
	public void toggle_checkbox(String type, String accessName) throws Exception {
		miscmethodObj.validateLocator(type);
		inputObj.toggleCheckbox(type, accessName);
	}

	// step to select radio button
	@Then("^I select radio button having (.+) \"(.*?)\"$")
	public void select_radio_button(String type, String accessName) throws Exception {
		miscmethodObj.validateLocator(type);
		inputObj.selectRadioButton(type, accessName);
	}

	// steps to select option by text from radio button group
	@Then("^I select \"(.*?)\" option by (.+) from radio button group having (.+) \"(.*?)\"$")
	public void select_option_from_radio_btn_group(String option, String by, String type, String accessName)
			throws Exception {
		miscmethodObj.validateLocator(type);
		// miscmethodObj.validateOptionBy(optionBy);
		inputObj.selectOptionFromRadioButtonGroup(type, option, by, accessName);
	}

	// Click element Steps

	// click on web element
	@Then("^I click on element having (.+) \"(.*?)\"$")
	public void click(String type, String accessName) throws Exception {
		miscmethodObj.validateLocator(type);
		clickObj.click(type, accessName);
	}

	// Forcefully click on element
	@Then("^I forcefully click on element having (.+) \"(.*?)\"$")
	public void click_forcefully(String type, String accessName) throws Exception {
		miscmethodObj.validateLocator(type);
		clickObj.clickForcefully(type, accessName);
	}

	// double click on web element
	@Then("^I double click on element having (.+) \"(.*?)\"$")
	public void double_click(String type, String accessValue) throws Exception {
		miscmethodObj.validateLocator(type);
		clickObj.doubleClick(type, accessValue);
	}

	// steps to click on link
	@Then("^I click on link having text \"(.*?)\"$")
	public void click_link(String accessName) {
		clickObj.click("linkText", accessName);
	}

	// Step to click on partial link
	@Then("^I click on link having partial text \"(.*?)\"$")
	public void click_partial_link(String accessName) {
		clickObj.click("partialLinkText", accessName);
	}

	// Progress methods

	// wait for specific period of time
	@Then("^I wait for (\\d+) sec$")
	public void wait(String time) throws NumberFormatException, InterruptedException {
		progressObj.wait(time);
	}

	@Then("^I wait for load (\\d+) sec$")
	public void waitForLoad(String time) throws NumberFormatException, InterruptedException {
		progressObj.waitForLoad(time);
	}
	// wait for specific element to display for specific period of time
	@Then("^I wait (\\d+) seconds for element having (.+) \"(.*?)\" to display$")
	public void wait_for_ele_to_display(String duration, String type, String accessName) throws Exception {
		miscmethodObj.validateLocator(type);
		progressObj.waitForElementToDisplay(type, accessName, duration);
	}

	@Then("^I wait for element having (.+) \"(.*?)\" to display$")
	public void wait_for_ele_to_display(String type, String accessName) throws Exception {
		miscmethodObj.validateLocator(type);
		progressObj.waitForElementToDisplay(type, accessName);
	}

	// wait for specific element to enable for specific period of time
	@Then("^I wait (\\d+) seconds for element having (.+) \"(.*?)\" to be enabled$")
	public void wait_for_ele_to_click(String duration, String type, String accessName) throws Exception {
		miscmethodObj.validateLocator(type);
		progressObj.waitForElementToClick(type, accessName, duration);
	}

	// JavaScript handling steps

	// Step to handle java script
	@Then("^I accept alert$")
	public void handle_alert() {
		javascriptObj.handleAlert("accept");
	}

	// Steps to dismiss java script
	@Then("^I dismiss alert$")
	public void dismiss_alert() {
		javascriptObj.handleAlert("dismiss");
	}

	// Screen shot methods

	@Then("^I take screenshot$")
	public void take_screenshot() throws IOException {
		screenshotObj.takeScreenShot();
	}

	// Configuration steps

	// step to print configuration
	@Then("^I print configuration$")
	public void print_config() {
		configObj.printDesktopConfiguration();
	}

	@Then("^I enter from cookie key \"([^\"]*)\"  into input field having (.+) \"([^\"]*)\"$")
	public void i_enter_from_cookie_key_into_input_field_having_id(String key, String type, String accessName)
			throws Throwable {
		miscmethodObj.validateLocator(type);
		String text = cookieObj.getVal(key);
		inputObj.enterText(type, text, accessName);
	}

	@Then("^if verifycode displayed then input verifycode$")
	public void if_verifycode_displayed_then_input_verifycode() throws Throwable {
		String type = "id";
		String key = "CODE";
		String accessName = "al_c";
		if (inputObj.checkElementIsDisplayed(type, accessName)) {

			miscmethodObj.validateLocator(type);
			String text = cookieObj.getVal(key);
			inputObj.enterText(type, text, accessName);
		}
	}
	@Then("^I delete cookies$")
    public void deleteCookies(){
    	cookieObj.deleteCookies();
    	
    }
	
	@Then("^check order_data from db whith param \"([^\"]*)\" and param \"([^\"]*)\" should be \"([^\"]*)\"$")
	public void check_order_data_from_db_whith_param_and_param_should_be(String arg1, String arg2, String arg3)
			throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		MySqlUtils.checkOrderData(arg1, arg2, arg3);
	}

	@Then("^check order_dataa from db whith param \"([^\"]*)\" and param \"([^\"]*)\" should be \"([^\"]*)\"$")
	public void check_order_dataa_from_db_whith_param_and_param_should_be(String arg1, String arg2, String arg3)
			throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		MySqlUtils.checkOrderData1(arg1, arg2, arg3);
	}

	@Then("^check order_datae from db whith param \"([^\"]*)\" and param \"([^\"]*)\" should be \"([^\"]*)\"$")
	public void check_order_datae_from_db_whith_param_and_param_should_be(String arg1, String arg2, String arg3)
			throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		MySqlUtils.checkOrderData3(arg1, arg2, arg3);
	}
	
	@Then("^I enter from redis key \"([^\"]*)\" into input field having (.+) \"([^\"]*)\"$")
	public void i_enter_from_redis_key_into_input_field_having_id(String key, String type, String accessName)
			throws Throwable {
		miscmethodObj.validateLocator(type);
		String text =RedisUtils.getValidateCode("10.10.29.33", 6379,"ValidateCode2_"+key);
		inputObj.enterText(type, text, accessName);
	}
	@Then("^if verifycode displayed then input verifycode having (.+) \"([^\"]*)\"$")
	public void if_verifycode_displayed_then_input_verifycode_having_id(String type, String accessName)
			throws Throwable {
		String key="CODE";
		if(inputObj.checkElementIsDisplayed(type, accessName)){
			
			miscmethodObj.validateLocator(type);
			String text = cookieObj.getVal(key);
			inputObj.enterText(type, text, accessName);
		}
	}
	
	@Then("^I unbind mobile (.+) from Cookie key$")
	public void i_unbind_mobile_from_cookie_key(String mobile)
			throws Throwable {
		 	String key="userid";
			String uid = cookieObj.getVal(key);
		    String gwAuthorizationStr="http://topsecret.vip.xunlei.com:8888/service/gw_authorization?request=add&userid="+uid+"&remark=%E5%86%85%E9%83%A8%E6%B5%8B%E8%AF%95";	
			String unbindStr="http://topsecret.vip.xunlei.com:8888/service/gateway?request=unbind&userid="+uid+"&nametype=5";
			String setuserStr="http://topsecret.vip.xunlei.com:8888/service/gateway?request=setuserinfo&userid="+uid+"&field=mobile&value=";
			navigationObj.navigateTo(gwAuthorizationStr);
			Thread.sleep(3 * 1000); 	
			navigationObj.navigateTo(unbindStr);
			Thread.sleep(3 * 1000);
			navigationObj.navigateTo(setuserStr);
	}

	    //追加js点击
		@Then("^executejs (.+) \"(.*?)\"$")
		public void executejs(String accessType, String access_name)
				throws Throwable {

			javascriptObj.executejs(accessType,access_name);
		}
	    
		@Then("^check order_data from db whith param \"([^\"]*)\" should be \"([^\"]*)\"$")
		public void checkPayMoney(String arg1,String arg2)
				throws Throwable {
			// Write code here that turns the phrase above into concrete actions
			MySqlUtils.checkPayMoney(arg1, arg2);
		}
		@Then("^I kill process$")
		 public static void operateWindowsProcess() {
	         NavigateMethods.operateWindowsProcess();
	     }
		@Then("^I clear localStorage$")
		  public void clearLocalStorage(){
			  javascriptObj.executejs("localStorage.clear");
		  }
		
}