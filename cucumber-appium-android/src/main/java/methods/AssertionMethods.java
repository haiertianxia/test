package methods;

import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AssertionMethods extends SelectElementByType {
	private WebDriver driver;
	private  WebDriverWait wait ;
	private WebElement element;

	public AssertionMethods(WebDriver driver,WebDriverWait wait) {
		element = null;
		this.driver=driver;
		this.wait=wait;
	}

	public String getPageTitle() {
		return driver.getTitle();
	}

	public void checkTitle(String title, boolean testCase) throws TestCaseFailed {
		String pageTitle = getPageTitle();
		if (testCase) {
			if (!pageTitle.equals(title))
				throw new TestCaseFailed((new StringBuilder("Page Title Not Matched, Actual Page Title : "))
						.append(pageTitle).toString());
		} else if (pageTitle.equals(title))
			throw new TestCaseFailed(
					(new StringBuilder("Page Title Matched, Actual Page Title : ")).append(pageTitle).toString());
	}

	public void checkPartialTitle(String partialTitle, boolean testCase) throws TestCaseFailed {
		String pageTitle = getPageTitle();
		if (testCase) {
			if (!pageTitle.contains(partialTitle))
				throw new TestCaseFailed((new StringBuilder("Partial Page Title Not Present, Actual Page Title : "))
						.append(pageTitle).toString());
		} else if (pageTitle.contains(partialTitle))
			throw new TestCaseFailed((new StringBuilder("Partial Page Title Present, Actual Page Title : "))
					.append(pageTitle).toString());
	}

	public String getElementText(String accessType, String accessName) {
		element = (WebElement) wait
				.until(ExpectedConditions.presenceOfElementLocated(getelementbytype(accessType, accessName)));
		return element.getText();
	}

	public void checkElementText(String accessType, String actualValue, String accessName, boolean testCase)
			throws TestCaseFailed {
		String elementText = getElementText(accessType, accessName);
		if (testCase) {
			if (!elementText.equals(actualValue))
				throw new TestCaseFailed("Text Not Matched");
		} else if (elementText.equals(actualValue))
			throw new TestCaseFailed("Text Matched");
	}

	public void checkElementPartialText(String accessType, String actualValue, String accessName, boolean testCase)
			throws TestCaseFailed {
		String elementText = getElementText(accessType, accessName);
		if (testCase) {
			if (!elementText.contains(actualValue))
				throw new TestCaseFailed("Text Not Matched");
		} else if (elementText.contains(actualValue))
			throw new TestCaseFailed("Text Matched");
	}

	public boolean isElementEnabled(String accessType, String accessName) {
		element = (WebElement) wait
				.until(ExpectedConditions.presenceOfElementLocated(getelementbytype(accessType, accessName)));
		return element.isEnabled();
	}

	public void checkElementEnable(String accessType, String accessName, boolean testCase) throws TestCaseFailed {
		boolean result = isElementEnabled(accessType, accessName);
		if (testCase) {
			if (!result)
				throw new TestCaseFailed("Element Not Enabled");
		} else if (result)
			throw new TestCaseFailed("Element Enabled");
	}

	public String getCurrentUrl() {
		return driver.getCurrentUrl();
	}
	
	public void checkCurrentUrl(String url, boolean testCase) throws TestCaseFailed {
		String pageUrl = getCurrentUrl();
		if (testCase) {
			if (!pageUrl.equals(url))
				throw new TestCaseFailed((new StringBuilder("Page Url Not Matched, Actual Page Url : "))
						.append(pageUrl).toString());
		} else if (pageUrl.equals(url))
			throw new TestCaseFailed(
					(new StringBuilder("Page Url Matched, Actual Page Url : ")).append(pageUrl).toString());
	}
	
	public String getElementAttribute(String accessType, String accessName, String attributeName) {
		element = (WebElement) wait
				.until(ExpectedConditions.presenceOfElementLocated(getelementbytype(accessType, accessName)));
		return element.getAttribute(attributeName);
	}

	public void checkElementAttribute(String accessType, String attributeName, String attributeValue, String accessName,
			boolean testCase) throws TestCaseFailed {
		String attrVal = getElementAttribute(accessType, accessName, attributeName);
		if (testCase) {
			if (!attrVal.equals(attributeValue))
				throw new TestCaseFailed("Attribute Value Not Matched");
		} else if (attrVal.equals(attributeValue))
			throw new TestCaseFailed("Attribute Value Matched");
	}

	public boolean isElementDisplayed(String accessType, String accessName) {
		element = (WebElement) wait
				.until(ExpectedConditions.presenceOfElementLocated(getelementbytype(accessType, accessName)));
		return element.isDisplayed();
	}

	public void checkElementPresence(String accessType, String accessName, boolean testCase) throws TestCaseFailed {
		if (testCase) {
			if (!isElementDisplayed(accessType, accessName))
				throw new TestCaseFailed("Element Not Present");
		} else {
			try {
				if (isElementDisplayed(accessType, accessName))
					throw new Exception("Present");
			} catch (Exception e) {
				if (e.getMessage().equals("Present"))
					throw new TestCaseFailed("Element Present");
			}
		}
	}

	public void isCheckboxChecked(String accessType, String accessName, boolean shouldBeChecked) throws TestCaseFailed {
		WebElement checkbox = (WebElement) wait
				.until(ExpectedConditions.presenceOfElementLocated(getelementbytype(accessType, accessName)));
		if (!checkbox.isSelected() && shouldBeChecked)
			throw new TestCaseFailed("Checkbox is not checked");
		if (checkbox.isSelected() && !shouldBeChecked)
			throw new TestCaseFailed("Checkbox is checked");
		else
			return;
	}

	public void isRadioButtonSelected(String accessType, String accessName, boolean shouldBeSelected)
			throws TestCaseFailed {
		WebElement radioButton = (WebElement) wait
				.until(ExpectedConditions.presenceOfElementLocated(getelementbytype(accessType, accessName)));
		if (!radioButton.isSelected() && shouldBeSelected)
			throw new TestCaseFailed("Radio Button not selected");
		if (radioButton.isSelected() && !shouldBeSelected)
			throw new TestCaseFailed("Radio Button is selected");
		else
			return;
	}

	public void isOptionFromRadioButtonGroupSelected(String accessType, String by, String option, String accessName,
			boolean shouldBeSelected) throws TestCaseFailed {
		List radioButtonGroup = (List) wait
				.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getelementbytype(accessType, accessName)));
		for (Iterator iterator = radioButtonGroup.iterator(); iterator.hasNext();) {
			WebElement rb = (WebElement) iterator.next();
			if (by.equals("value")) {
				if (rb.getAttribute("value").equals(option)) {
					if (!rb.isSelected() && shouldBeSelected)
						throw new TestCaseFailed("Radio Button not selected");
					if (rb.isSelected() && !shouldBeSelected)
						throw new TestCaseFailed("Radio Button is selected");
				}
			} else if (rb.getText().equals(option)) {
				if (!rb.isSelected() && shouldBeSelected)
					throw new TestCaseFailed("Radio Button not selected");
				if (rb.isSelected() && !shouldBeSelected)
					throw new TestCaseFailed("Radio Button is selected");
			}
		}

	}

	public String getAlertText() {
		return driver.switchTo().alert().getText();
	}

	public void checkAlertText(String text) throws TestCaseFailed {
		if (!getAlertText().equals(text))
			throw new TestCaseFailed("Text on alert pop up not matched");
		else
			return;
	}

	public void isOptionFromDropdownSelected(String accessType, String by, String option, String accessName,
			boolean shouldBeSelected) throws TestCaseFailed {
		Select selectList = null;
		WebElement dropdown = (WebElement) wait
				.until(ExpectedConditions.presenceOfElementLocated(getelementbytype(accessType, accessName)));
		selectList = new Select(dropdown);
		String actualValue = "";
		if (by.equals("text"))
			actualValue = selectList.getFirstSelectedOption().getText();
		else
			actualValue = selectList.getFirstSelectedOption().getAttribute("value");
		if (!actualValue.equals(option) && shouldBeSelected)
			throw new TestCaseFailed("Option Not Selected From Dropwdown");
		if (actualValue.equals(option) && !shouldBeSelected)
			throw new TestCaseFailed("Option Selected From Dropwdown");
		else
			return;
	}
}