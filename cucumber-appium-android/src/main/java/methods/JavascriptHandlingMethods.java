/*** Eclipse Class Decompiler plugin, copyright (c) 2016 Chen Chao (cnfree2000@hotmail.com) ***/

package methods;

import org.openqa.selenium.WebDriver;

public class JavascriptHandlingMethods {
	private WebDriver driver;
	public void handleAlert(String decision) {
		if (decision.equals("accept"))
			driver.switchTo().alert().accept();
		else
			driver.switchTo().alert().dismiss();
	}
	public JavascriptHandlingMethods(WebDriver driver) {
		super();
		this.driver = driver;
	}
	
}