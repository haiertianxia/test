package methods;

import java.util.List;

import org.openqa.selenium.WebElement;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

/**
 * @author xl
 *
 */
public class SelectElement {

	private AppiumDriver<? extends MobileElement> driver;

	public SelectElement(AppiumDriver<? extends MobileElement> driver) {
		this.driver = driver;
	}

	/**
	 * 
	 * List<WebElement> lis = driver.findElementsByAndroidUIAutomator(
	 * "new UiSelector().className("+"android.widget.ImageView"+").index(4)");
	 * 
	 * @param className
	 * @param index
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<WebElement> getElementsByClassAndIndex(String className, int index) {
		List<WebElement> lis = null;
		lis = ((AndroidDriver) driver)
				.findElementsByAndroidUIAutomator("new UiSelector().className(" + className + ").index(" + index + ")");
		return lis;
	}

	/**
	 * List<WebElement> lis = driver.findElementsByAndroidUIAutomator(
	 * "new UiSelector().className("
	 * +"android.widget.ImageView"+").index(4).clickable(true)");
	 * 
	 * @param classname
	 * @param index
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<WebElement> getElementsByClassAndIndexAndClickable(String classname, int index) {
		List<WebElement> lis = null;
		lis = ((AndroidDriver) driver).findElementsByAndroidUIAutomator(
				"new UiSelector().className(" + classname + ").index(" + index + ").clickable(true)");
		return lis;
	}

	/**
	 * driver.findElementByAndroidUIAutomator("new UiSelector().index("
	 * +index+")");
	 * 
	 * @param index
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public WebElement getElementByIndex(int index) {
		return ((AndroidDriver) driver).findElementByAndroidUIAutomator("new UiSelector().index(" + index + ")");
	}

	public WebElement getElementByType(String type, String accessName) {
		String s;
		switch ((s = type).hashCode()) {
		default:
			break;

		/*
		 * 通过ID定位，打开UI Automator Viewer, node
		 * detail下找到标签是“resource-id”—>”com.taobao.taobao:id/tv_title”
		 * driver.findElementById("com.taobao.taobao:id/tv_title");
		 */
		case 3355:
			if (s.equals("id"))
				return driver.findElementById(accessName);
			break;

		/*
		 * 针对谷歌浏览器打开的百度首页，搜索按钮的定位: 通过AccessibilityId定位，打开UI Automator Viewer,
		 * node detail下找到标签是“content-desc”—>“百度一下
		 * driver.findElementByAccessibilityId("百度一下");
		 * 
		 */
		case 1331736329:
			if (s.equals("accessibilityId"))
				return driver.findElementByAccessibilityId(accessName);
			break;

		/*
		 * 通过classname定位（一般情况下，最好不要通过classname来进行元素的定位，避免出现重复）， 打开UI Automator
		 * Viewer, node detail下找到标签是“class”—>“android.widget.TextView”
		 * driver.findElementByClassName("android.widget.TextView");
		 * 
		 */
		case 94742904:
			if (s.equals("class"))
				return driver.findElementByClassName(accessName);
			break;

		case 98819:
			if (s.equals("css"))
				return driver.findElementByCssSelector(accessName);
			break;

		/*
		 * 针对taobao，购物车的定位: 通过name定位，打开UI Automator Viewer, node
		 * detail下找到标签是“text”—>“购物车” driver.findElementByName("购物车");
		 */
		case 3373707:
			if (s.equals("name"))
				return driver.findElementByName(accessName);
			break;

		case 114256029:
			if (s.equals("xpath"))
				return driver.findElementByXPath(accessName);
			break;

		case -1549184699:
			if (s.equals("tagName"))
				return driver.findElementById(accessName);
			break;

		case 292026600:
			if (s.equals("partialLinkText"))
				return driver.findElementByPartialLinkText(accessName);
			break;

		case 1194187847:
			if (s.equals("linkText"))
				return driver.findElementByLinkText(accessName);
			break;
		}
		return null;
	}

	public WebElement getElementsByClassNameAndIndex(String accessName, int index) {
		WebElement e=null;
		
		try {
			List<? extends MobileElement> lst=driver.findElementsByClassName(accessName);
			if(lst!=null){
				e=lst.get(index);
			}
		} catch (Exception e1) {
			System.err.println("获取元素失败：Class Name："+accessName +"; index = " + index);
			e1.printStackTrace();
		}
		return e;
	}

}