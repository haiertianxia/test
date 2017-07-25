/*** Eclipse Class Decompiler plugin, copyright (c) 2016 Chen Chao (cnfree2000@hotmail.com) ***/

package methods;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ClickElementsMethods extends SelectElementByType {
	private WebDriver driver;
	private  WebDriverWait wait ;
	private WebElement element;

	public ClickElementsMethods(WebDriver driver, WebDriverWait wait ) {
		element = null;
		this.driver=driver;
		this.wait=wait;
	}

	public void click(String accessType, String accessName) {
		element = (WebElement) wait
				.until(ExpectedConditions.presenceOfElementLocated(getelementbytype(accessType, accessName)));
		element.click();
	}

	public void clickForcefully(String accessType, String accessName) {
		element = (WebElement) wait
				.until(ExpectedConditions.presenceOfElementLocated(getelementbytype(accessType, accessName)));
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", new Object[] { element });
	}

	public void doubleClick(String accessType, String accessValue) {
		element = (WebElement) wait
				.until(ExpectedConditions.presenceOfElementLocated(getelementbytype(accessType, accessValue)));
		Actions action = new Actions(driver);
		action.moveToElement(element).doubleClick().perform();
	}
}