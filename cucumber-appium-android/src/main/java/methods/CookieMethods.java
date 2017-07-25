package methods;

import org.openqa.selenium.WebDriver;

public class CookieMethods {
	private WebDriver driver;

	public CookieMethods(WebDriver driver) {
		super();
		this.driver = driver;
	}

	public String getVal(String key) {
		String value = null;
		if (driver.manage().getCookieNamed(key) != null) {
			value = driver.manage().getCookieNamed(key).getValue();
		}
		return value;
	}
}
