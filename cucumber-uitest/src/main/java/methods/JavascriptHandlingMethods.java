/*** Eclipse Class Decompiler plugin, copyright (c) 2016 Chen Chao (cnfree2000@hotmail.com) ***/

package methods;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class JavascriptHandlingMethods extends SelectElementByType {
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
	public void executejs(String accessType, String accessName){
		//WebElement e=driver.findElement(By.cssSelector(".btn_chg"));
		JavascriptExecutor js = (JavascriptExecutor) driver; 
		js.executeScript("arguments[0].click()", driver.findElement(getelementbytype(accessType, accessName))); 
		
	
	}
	
	public void executejs(String script){
		JavascriptExecutor js = (JavascriptExecutor) driver; 
		js.executeScript(script); 
		
	
	}
	
}