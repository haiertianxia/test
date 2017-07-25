/*** Eclipse Class Decompiler plugin, copyright (c) 2016 Chen Chao (cnfree2000@hotmail.com) ***/

package methods;

import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class InputMethods extends SelectElementByType {
	private WebDriver driver;
	private  WebDriverWait wait;
	private WebElement dropdown;
	private Select selectList;

	public InputMethods(WebDriver driver, WebDriverWait wait ) {
		dropdown = null;
		selectList = null;
		this.driver=driver;
		this.wait=wait;
	}

	public void enterText(String accessType, String text, String accessName) {
		wait.until(ExpectedConditions.presenceOfElementLocated(getelementbytype(accessType, accessName)));
		driver.findElement(getelementbytype(accessType, accessName)).sendKeys(new CharSequence[] { text });
	}

	public void clearText(String accessType, String accessName) {
		wait.until(ExpectedConditions.presenceOfElementLocated(getelementbytype(accessType, accessName)));
		driver.findElement(getelementbytype(accessType, accessName)).clear();
	}

	public void selectelementfromdropdownbytype(Select select_list, String bytype, String option) {
		if (bytype.equals("selectByIndex")) {
			int index = Integer.parseInt(option);
			select_list.selectByIndex(index - 1);
		} else if (bytype.equals("value"))
			select_list.selectByValue(option);
		else if (bytype.equals("text"))
			select_list.selectByVisibleText(option);
	}

	public void selectOptionFromDropdown(String accessType, String optionBy, String option, String accessName) {
		dropdown = (WebElement) wait
				.until(ExpectedConditions.presenceOfElementLocated(getelementbytype(accessType, accessName)));
		selectList = new Select(dropdown);
		if (optionBy.equals("selectByIndex"))
			selectList.selectByIndex(Integer.parseInt(option) - 1);
		else if (optionBy.equals("value"))
			selectList.selectByValue(option);
		else if (optionBy.equals("text"))
			selectList.selectByVisibleText(option);
	}

	public void unselectAllOptionFromMultiselectDropdown(String accessType, String accessName) {
		dropdown = (WebElement) wait
				.until(ExpectedConditions.presenceOfElementLocated(getelementbytype(accessType, accessName)));
		selectList = new Select(dropdown);
		selectList.deselectAll();
	}

	public void deselectOptionFromDropdown(String accessType, String optionBy, String option, String accessName) {
		dropdown = (WebElement) wait
				.until(ExpectedConditions.presenceOfElementLocated(getelementbytype(accessType, accessName)));
		selectList = new Select(dropdown);
		if (optionBy.equals("selectByIndex"))
			selectList.deselectByIndex(Integer.parseInt(option) - 1);
		else if (optionBy.equals("value"))
			selectList.deselectByValue(option);
		else if (optionBy.equals("text"))
			selectList.deselectByVisibleText(option);
	}

	public void checkCheckbox(String accessType, String accessName) {
		WebElement checkbox = (WebElement) wait
				.until(ExpectedConditions.presenceOfElementLocated(getelementbytype(accessType, accessName)));
		if (!checkbox.isSelected())
			checkbox.click();
	}

	public void uncheckCheckbox(String accessType, String accessName) {
		WebElement checkbox = (WebElement) wait
				.until(ExpectedConditions.presenceOfElementLocated(getelementbytype(accessType, accessName)));
		if (checkbox.isSelected())
			checkbox.click();
	}

	public void toggleCheckbox(String accessType, String accessName) {
		((WebElement) wait.until(ExpectedConditions.presenceOfElementLocated(getelementbytype(accessType, accessName))))
				.click();
	}

	public void selectRadioButton(String accessType, String accessName) {
		WebElement radioButton = (WebElement) wait
				.until(ExpectedConditions.presenceOfElementLocated(getelementbytype(accessType, accessName)));
		if (!radioButton.isSelected())
			radioButton.click();
	}

	public void selectOptionFromRadioButtonGroup(String accessType, String option, String by, String accessName) {
		List radioButtonGroup = driver.findElements(getelementbytype(accessType, accessName));
		for (Iterator iterator = radioButtonGroup.iterator(); iterator.hasNext();) {
			WebElement rb = (WebElement) iterator.next();
			if (by.equals("value")) {
				if (rb.getAttribute("value").equals(option) && !rb.isSelected())
					rb.click();
			} else if (by.equals("text") && rb.getText().equals(option) && !rb.isSelected())
				rb.click();
		}

	}
	
	public boolean checkElementIsDisplayed(String accessType, String accessName){
		return driver.findElement(getelementbytype(accessType, accessName)).isDisplayed();
	}
}