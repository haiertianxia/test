/*** Eclipse Class Decompiler plugin, copyright (c) 2016 Chen Chao (cnfree2000@hotmail.com) ***/

package methods;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;

public class ProgressMethods extends SelectElementByType {
	private WebDriver driver;
	private  WebDriverWait wait ;
	public ProgressMethods(WebDriver driver, WebDriverWait wait ) {
		this.driver = driver;
		this.wait=wait;
	}

	public void wait(String time) throws NumberFormatException, InterruptedException {
		Thread.sleep(Integer.parseInt(time) * 1000);
	}

	public void waitForLoad(String duration) {
		WebDriverWait wait = new WebDriverWait(driver, Integer.parseInt(duration) * 1000);
		wait.until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
            }
        });
	}
	public void waitForElementToDisplay(String accessType, String accessName, String duration) {
		org.openqa.selenium.By byEle = getelementbytype(accessType, accessName);
		WebDriverWait wait = new WebDriverWait(driver, Integer.parseInt(duration) * 1000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(byEle));
	}

	public void waitForElementToDisplay(String accessType, String accessName) {
		org.openqa.selenium.By byEle = getelementbytype(accessType, accessName);
		wait.until(ExpectedConditions.visibilityOfElementLocated(byEle));
	}
	public void waitForElementToClick(String accessType, String accessName, String duration) {
		org.openqa.selenium.By byEle = getelementbytype(accessType, accessName);
		WebDriverWait wait = new WebDriverWait(driver, Integer.parseInt(duration) * 1000);
		wait.until(ExpectedConditions.elementToBeClickable(byEle));
	}
}