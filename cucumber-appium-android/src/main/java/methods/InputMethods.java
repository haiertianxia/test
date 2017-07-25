package methods;

import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class InputMethods {
	private AppiumDriver<? extends MobileElement> driver;
	private SelectElement select;

	public InputMethods(AppiumDriver<? extends MobileElement> driver) {
		
		this.driver=driver;
		this.select=new SelectElement(driver);
	}

	public void enterText(String accessType, String text, String accessName) {
		select.getElementByType(accessType, accessName).sendKeys(new CharSequence[] { text });
	}

	public void clearText(String accessType, String accessName) {
		WebElement e=select.getElementByType(accessType, accessName);
		e.clear();
		clearEleText(e);
	}

	public void clearTextByClassNameAndIndex( String accessName,int index) {
		WebElement e=select.getElementsByClassNameAndIndex(accessName, index);
		e.clear();
		clearEleText(e);
	}
	

	public void sleep(int sencond) {
		try {
			Thread.sleep(1000 * sencond);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	private void clearEleText(WebElement ele) {
		sleep(2);
		ele.click();
		String text = ele.getAttribute("text");
		System.out.println(text);
		System.out.println("text length:" + text.length());
		sleep(2);
		clearText(text);
//		 ele.clear();
	}
	
	/**
	 * This method for delete text in textView or textEdit
	 * 
	 * @author Young
	 * @param text
	 */
	@SuppressWarnings({ "unchecked" })
	private void clearText(String text) {
		((AndroidDriver<AndroidElement>) driver).pressKeyCode(123);
		for (int i = 0; i < text.length(); i++) {
			((AndroidDriver<AndroidElement>) driver).pressKeyCode(67);
		}
	}

	public void enterTextByClassNameAndIndex(String className, String text, int index) {
		WebElement e=select.getElementsByClassNameAndIndex(className, index);
		e.sendKeys(new CharSequence[] { text });
		
	}
	
//	public void selectelementfromdropdownbytype(Select select_list, String bytype, String option) {
//		if (bytype.equals("selectByIndex")) {
//			int index = Integer.parseInt(option);
//			select_list.selectByIndex(index - 1);
//		} else if (bytype.equals("value"))
//			select_list.selectByValue(option);
//		else if (bytype.equals("text"))
//			select_list.selectByVisibleText(option);
//	}
//
//	public void selectOptionFromDropdown(String accessType, String optionBy, String option, String accessName) {
//		dropdown = (WebElement) wait
//				.until(ExpectedConditions.presenceOfElementLocated(getelementbytype(accessType, accessName)));
//		selectList = new Select(dropdown);
//		if (optionBy.equals("selectByIndex"))
//			selectList.selectByIndex(Integer.parseInt(option) - 1);
//		else if (optionBy.equals("value"))
//			selectList.selectByValue(option);
//		else if (optionBy.equals("text"))
//			selectList.selectByVisibleText(option);
//	}
//
//	public void unselectAllOptionFromMultiselectDropdown(String accessType, String accessName) {
//		dropdown = (WebElement) wait
//				.until(ExpectedConditions.presenceOfElementLocated(getelementbytype(accessType, accessName)));
//		selectList = new Select(dropdown);
//		selectList.deselectAll();
//	}
//
//	public void deselectOptionFromDropdown(String accessType, String optionBy, String option, String accessName) {
//		dropdown = (WebElement) wait
//				.until(ExpectedConditions.presenceOfElementLocated(getelementbytype(accessType, accessName)));
//		selectList = new Select(dropdown);
//		if (optionBy.equals("selectByIndex"))
//			selectList.deselectByIndex(Integer.parseInt(option) - 1);
//		else if (optionBy.equals("value"))
//			selectList.deselectByValue(option);
//		else if (optionBy.equals("text"))
//			selectList.deselectByVisibleText(option);
//	}
//
//	public void checkCheckbox(String accessType, String accessName) {
//		WebElement checkbox = (WebElement) wait
//				.until(ExpectedConditions.presenceOfElementLocated(getelementbytype(accessType, accessName)));
//		if (!checkbox.isSelected())
//			checkbox.click();
//	}
//
//	public void uncheckCheckbox(String accessType, String accessName) {
//		WebElement checkbox = (WebElement) wait
//				.until(ExpectedConditions.presenceOfElementLocated(getelementbytype(accessType, accessName)));
//		if (checkbox.isSelected())
//			checkbox.click();
//	}
//
//	public void toggleCheckbox(String accessType, String accessName) {
//		((WebElement) wait.until(ExpectedConditions.presenceOfElementLocated(getelementbytype(accessType, accessName))))
//				.click();
//	}
//
//	public void selectRadioButton(String accessType, String accessName) {
//		WebElement radioButton = (WebElement) wait
//				.until(ExpectedConditions.presenceOfElementLocated(getelementbytype(accessType, accessName)));
//		if (!radioButton.isSelected())
//			radioButton.click();
//	}
//
//	public void selectOptionFromRadioButtonGroup(String accessType, String option, String by, String accessName) {
//		List radioButtonGroup = driver.findElements(getelementbytype(accessType, accessName));
//		for (Iterator iterator = radioButtonGroup.iterator(); iterator.hasNext();) {
//			WebElement rb = (WebElement) iterator.next();
//			if (by.equals("value")) {
//				if (rb.getAttribute("value").equals(option) && !rb.isSelected())
//					rb.click();
//			} else if (by.equals("text") && rb.getText().equals(option) && !rb.isSelected())
//				rb.click();
//		}
//
//	}
}