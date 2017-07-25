/*** Eclipse Class Decompiler plugin, copyright (c) 2016 Chen Chao (cnfree2000@hotmail.com) ***/

package methods;

import java.util.Iterator;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NavigateMethods extends SelectElementByType {
	private WebDriver driver;
	private WebDriverWait wait;
	private WebElement element;
	private String old_win;
	private String lastWinHandle;

	public NavigateMethods(WebDriver driver,WebDriverWait wait) {
		element = null;
		old_win = null;
		this.driver = driver;
		this.wait=wait;
	}

	public void navigateTo(String url) {
		driver.get(url);
	}

	public void navigate(String direction) {
		if (direction.equals("back"))
			driver.navigate().back();
		else
			driver.navigate().forward();
	}

	public void closeDriver() {
		driver.close();

	}

	public Keys getKey() {
		String os = System.getProperty("os.name").toLowerCase();
		if (os.contains("win"))
			return Keys.CONTROL;
		if (os.contains("nux") || os.contains("nix"))
			return Keys.CONTROL;
		if (os.contains("mac"))
			return Keys.COMMAND;
		else
			return null;
	}

	public void zoomInOut(String inOut) {
		WebElement Sel = driver.findElement(getelementbytype("tagName", "html"));
		if (inOut.equals("ADD"))
			Sel.sendKeys(new CharSequence[] { Keys.chord(new CharSequence[] { getKey(), Keys.ADD }) });
		else if (inOut.equals("SUBTRACT"))
			Sel.sendKeys(new CharSequence[] { Keys.chord(new CharSequence[] { getKey(), Keys.SUBTRACT }) });
		else if (inOut.equals("reset"))
			Sel.sendKeys(new CharSequence[] { Keys.chord(new CharSequence[] { getKey(), Keys.NUMPAD0 }) });
	}

	public void zoomInOutTillElementDisplay(String accessType, String inOut, String accessName) {
		Actions action = new Actions(driver);
		for (element = (WebElement) wait
				.until(ExpectedConditions.presenceOfElementLocated(getelementbytype(accessType, accessName))); !element
						.isDisplayed(); action.keyDown(getKey()).sendKeys(new CharSequence[] { inOut }).keyUp(getKey())
								.perform())
			;
	}

	public void resizeBrowser(int width, int height) {
		driver.manage().window().setSize(new Dimension(width, height));
	}

	public void maximizeBrowser() {
		driver.manage().window().maximize();
	}

	public void hoverOverElement(String accessType, String accessName) {
		Actions action = new Actions(driver);
		element = (WebElement) wait
				.until(ExpectedConditions.presenceOfElementLocated(getelementbytype(accessType, accessName)));
		action.moveToElement(element).perform();
	}

	public void scrollToElement(String accessType, String accessName) {
		element = (WebElement) wait
				.until(ExpectedConditions.presenceOfElementLocated(getelementbytype(accessType, accessName)));
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].scrollIntoView();", new Object[] { element });
	}

	public void scrollPage(String to) throws Exception {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		if (to.equals("end"))
			executor.executeScript(
					"window.scrollTo(0,Math.max(document.documentElement.scrollHeight,document.body.scrollHeight,document.documentElement.clientHeight));",
					new Object[0]);
		else if (to.equals("top"))
			executor.executeScript(
					"window.scrollTo(Math.max(document.documentElement.scrollHeight,document.body.scrollHeight,document.documentElement.clientHeight),0);",
					new Object[0]);
		else
			throw new Exception("Exception : Invalid Direction (only scroll \"top\" or \"end\")");
	}

	public void switchToNewWindow() {
		old_win = driver.getWindowHandle();
		for (Iterator iterator = driver.getWindowHandles().iterator(); iterator.hasNext();) {
			String winHandle = (String) iterator.next();
			lastWinHandle = winHandle;
		}

		driver.switchTo().window(lastWinHandle);
	}

	public void switchToOldWindow() {
		driver.switchTo().window(old_win);
	}

	public void switchToWindowByTitle(String windowTitle) throws Exception {
		old_win = driver.getWindowHandle();
		boolean winFound = false;
		for (Iterator iterator = driver.getWindowHandles().iterator(); iterator.hasNext();) {
			String winHandle = (String) iterator.next();
			String str = driver.switchTo().window(winHandle).getTitle();
			if (str.equals(windowTitle)) {
				winFound = true;
				break;
			}
		}

		if (!winFound)
			throw new Exception(
					(new StringBuilder("Window having title ")).append(windowTitle).append(" not found").toString());
		else
			return;
	}

	public void closeNewWindow() {
		driver.close();
	}

	public void switchFrame(String accessType, String accessName) {
		if (accessType.equalsIgnoreCase("index")) {
			driver.switchTo().frame(accessName);
		} else {
			element = (WebElement) wait
					.until(ExpectedConditions.presenceOfElementLocated(getelementbytype(accessType, accessName)));
			driver.switchTo().frame(element);
		}
	}

	public void switchToDefaultContent() {
		driver.switchTo().defaultContent();
	}
}