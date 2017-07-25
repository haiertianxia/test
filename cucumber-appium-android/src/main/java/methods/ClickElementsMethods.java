package methods;

import org.openqa.selenium.WebElement;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class ClickElementsMethods  {
	private WebElement element;
	private SelectElement select;
	public ClickElementsMethods(AppiumDriver<? extends MobileElement> driver) {
		element = null;
		this.select=new SelectElement(driver);
	}

	public void click(String accessType, String accessName) {
		
		element=select.getElementByType(accessType, accessName);
		element.click();
	}
}