/*** Eclipse Class Decompiler plugin, copyright (c) 2016 Chen Chao (cnfree2000@hotmail.com) ***/

package methods;

import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.os.WindowsUtils;
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

	public boolean switchToNewWindow(String windowTitle) {
		 boolean flag = false;
	        try {
	            //将页面上所有的windowshandle放在入set集合当中
//	            String currentHandle = dr.getWindowHandle();
	            Set<String> handles = driver.getWindowHandles();
	            for (String s : handles) {
	                // 先切换
	                String title = driver.switchTo().window(s).getTitle();
	                // System.out.println("title:"+title);
	                if (driver.getTitle().contains(windowTitle)) {
	                    flag = true;
	                    System.out.println("Switch to window: "
	                            + windowTitle + " successfully!");
	                    break;
	                } else {
	                    System.out.println("err ！");
	                    continue;
	                }
	            }
	        } catch (Exception e) {
	            System.out.printf("Window: " + windowTitle
	                    + " cound not found!", e.fillInStackTrace());
	            flag = false;
	        }
	        return flag;
	}

	public boolean switchToOldWindow(String windowTitle) {
		  boolean flag = false;
	        try {
	            //将页面上所有的windowshandle放在入set集合当中
//	            String currentHandle = dr.getWindowHandle();
	            Set<String> handles = driver.getWindowHandles();
	            for (String s : handles) {
	                // 先切换
	                String title = driver.switchTo().window(s).getTitle();
	                // System.out.println("title:"+title);
	                if (driver.getTitle().contains(windowTitle)) {
	                    flag = true;
	                    System.out.println("Switch to window: "
	                            + windowTitle + " successfully!");
	                    break;
	                } else {
	                    System.out.println("err ！");
	                    continue;
	                }
	            }
	        } catch (Exception e) {
	            System.out.printf("Window: " + windowTitle
	                    + " cound not found!", e.fillInStackTrace());
	            flag = false;
	        }
	        return flag;
	}

	public boolean closeWindowByTitle(String windowTitle) throws Exception {
		 boolean flag = false;
	        Set<String> handles = new HashSet<String>();
	        String oldwin = null;
	        try {
	            //将页面上所有的windowshandle放在入set集合当中
	            String currentHandle = driver.getWindowHandle();
	            oldwin = currentHandle;//当前的窗口是迅雷支付中心，赋值给oldwin

	            handles = driver.getWindowHandles();
	            for (Object s : handles) {
	                if (s.equals(currentHandle))
	                    continue;
	                else {
	                	driver = driver.switchTo().window(s.toString());

	                    if (driver.getTitle().contains(windowTitle)) {
	                        flag = true;
	                        System.out.println("Switch to window: "
	                                + windowTitle + " successfully!");
	                        System.out.println("switchTo equals" + driver.getCurrentUrl());
	                        driver.close();
	                        break;
	                    } else
	                        continue;
	                }
	            }
	        } catch (Exception e) {
	            System.out.printf("Window: " + windowTitle
	                    + " cound not found!", e.fillInStackTrace());
	            flag = false;
	        }

	        return flag;
	}

	public boolean closeTenyWindowByTitle(String windowTitle,String windowTitlePay) throws Exception {
		 boolean flag = false;
	        Set<String> handles = new HashSet<String>();
	        String oldwin = null;
	        try {
	            //将页面上所有的windowshandle放在入set集合当中
	            String currentHandle = driver.getWindowHandle();
	            oldwin = currentHandle;//当前的窗口是迅雷支付中心，赋值给oldwin

	            handles = driver.getWindowHandles();
	            for (Object s : handles) {
	                if (s.equals(currentHandle))
	                    continue;
	                else {
	                	driver = driver.switchTo().window(s.toString());

	                    if (driver.getTitle().contains(windowTitle)) {
	                        flag = true;
	                        System.out.println("Switch to window: "
	                                + windowTitle + " successfully!");
	                        System.out.println("switchTo equals" + driver.getCurrentUrl());
	                        driver.close();
	                        break;
	                    } else if (driver.getTitle().contains(windowTitlePay)) {
	                        flag = true;
	                        System.out.println("Switch to window: "
	                                + windowTitle + " successfully!");
	                        System.out.println("switchTo equals" + driver.getCurrentUrl());
	                        driver.close();
	                        break;

	                    } else {

	                        continue;
	                    }
	                }
	            }
	        } catch (Exception e) {
	            System.out.printf("Window: " + windowTitle
	                    + " cound not found!", e.fillInStackTrace());
	            flag = false;
	        }

	        return flag;
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
	
	public void switchToAlertAccept() {

	String text = driver.switchTo().alert().getText();
	System.out.println(text+"cess");
	}
	
	 public static void operateWindowsProcess(){
		try{
			WindowsUtils.tryToKillByName("chrome.exe");
		}catch(Exception e){
			e.printStackTrace();
		}
     }
}