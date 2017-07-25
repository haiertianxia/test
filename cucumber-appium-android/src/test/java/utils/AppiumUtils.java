package utils;

import java.io.File;
import java.net.URL;

import org.apache.commons.codec.binary.Base64;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.StartsActivity;

public class AppiumUtils {
	private AppiumDriver<AndroidElement> driver;

	public void setUp() throws Exception {
		// set up appium
		File classpathRoot = new File("src/test/resources/");
		File appDir = new File(classpathRoot, "/apps/androidaccount");
		// File appDir = new File("C:/apk/");
		File app = new File(appDir, "XlkDemo-1.6.5.177626-guanwang.apk");
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("deviceName", "android");
		capabilities.setCapability("app", app.getAbsolutePath());
		capabilities.setCapability("appPackage", "com.xunlei.xlkdemo");
		capabilities.setCapability("appActivity", ".XLKDemoActivity");
		capabilities.setCapability("unicodeKeyboard", "True");
		capabilities.setCapability("resetKeyboard", "True");
		driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
	}

	public void tearDown() throws Exception {
		driver.quit();
	}

	public void sleep(int sencond) {
		try {
			Thread.sleep(1000 * sencond);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method for delete text in textView or textEdit
	 * 
	 * @author Young
	 * @param text
	 */
	public void clearText(String text) {
		((AndroidDriver<AndroidElement>) driver).pressKeyCode(123);
		for (int i = 0; i < text.length(); i++) {
			((AndroidDriver<AndroidElement>) driver).pressKeyCode(67);
		}
	}

	public void clearEleText(WebElement ele) {
		sleep(2);
		ele.click();
		String text = ele.getAttribute("text");
		System.out.println(text);
		System.out.println("text length:" + text.length());
		sleep(2);
		clearText(text);
		// ele.clear();
	}

	/**
	 * 锁定屏幕
	 * 
	 * @param second
	 */
	public void lock(int second) {

		driver.lockScreen(second);
	}

	/**
	 * 将 app 置于后台
	 * 
	 * @param second
	 */
	public void runAppInBackground(int second) {

		driver.runAppInBackground(second);
	}

	/**
	 * 收起键盘
	 * 
	 * @param second
	 */
	public void hideKeyboard(int second) {

		driver.hideKeyboard();
	}

	/**
	 * 启动 Activity： 在当前应用中打开一个 activity 或者启动一个新应用并打开一个 activity 。 只能在 Android
	 * 上使用
	 * 
	 * @param appPackage
	 * @param appActivity
	 */
	public void startActivity(String appPackage, String appActivity) {

		((StartsActivity) driver).startActivity(appPackage, appActivity);
	}

	/**
	 * 打开通知栏 (Notifications)： 打开下拉通知栏 只能在 Android 上使用
	 */
	public void openNotifications() {

		((AndroidDriver<AndroidElement>) driver).openNotifications();
	}

	/**
	 * 是否已经安装： 检查应用是否已经安装
	 * 
	 * @param appPackage
	 */
	public void isAppInstalled(String appPackage) {

		driver.isAppInstalled(appPackage);
	}

	/**
	 * 安装应用： 安装应用到设备中去
	 * 
	 * @param appPath
	 */
	public void installApp(String appPath) {

		driver.installApp(appPath);
	}

	/**
	 * 删除应用： 从设备中删除一个应用
	 * 
	 * @param bundleId
	 */
	public void removeApp(String bundleId) {

		driver.removeApp(bundleId);
	}

	/**
	 * 摇晃 (Shake) 模拟设备摇晃
	 */
	public void shake() {
		// driver.shake();
	}

	/**
	 * 关闭应用
	 */
	public void closeApp() {

		driver.closeApp();
	}

	/**
	 * 启动 (Launch)
	 */
	public void launchApp() {

		driver.launchApp();
	}

	/**
	 * 重置 (Reset) 应用重置
	 */
	public void resetApp() {

		driver.resetApp();
	}

	/**
	 * 可用上下文 (context) 列出所有的可用上下文
	 */
	public void getContextHandles() {

		driver.getContextHandles();
	}

	/**
	 * 当前上下文 (context) 列出当前上下文
	 */
	public void getContext() {

		driver.getContext();
	}

	/**
	 * 切换到默认的上下文 (context) 将上下文切换到默认上下文
	 */
	public void context() {

		// driver.context(name);
		// driver.context();
	}

	/**
	 * 应用的字符串 (App Strings)
	 */
	public void getAppStrings() {

		driver.getAppStrings();
	}

	/**
	 * 按键事件 (Key Event) 给设备发送一个按键事件
	 */
	public void pressKeyCode(int key) {
		((AndroidDriver<AndroidElement>) driver).pressKeyCode(key);
	}

	/**
	 * 当前 Activity: 获取当前 activity。只能在 Android 上使用
	 */
	public void currentActivity() {

		((AndroidDriver<AndroidElement>) driver).currentActivity();
	}

	/**
	 * 触摸动作(TouchAction) / 多点触摸动作(MultiTouchAction)
	 * 
	 * @param el
	 * @param x
	 * @param y
	 */
	public void TouchAction(AndroidElement el, int x, int y) {

		// TouchAction action =
		new TouchAction(driver).press(el, x, y).release().perform();
	}

	/**
	 * 滑动(Swipe) 模拟用户滑动
	 * 
	 * @param startx
	 * @param starty
	 * @param endx
	 * @param endy
	 * @param duration
	 */
	public void swipe(int startx, int starty, int endx, int endy, int duration) {

		driver.swipe(75, 500, 75, 0, (int) 0.8);
	}

	/**
	 * 捏 (Pinch)： 捏屏幕 (双指往内移动来缩小屏幕)
	 * 
	 * @param el
	 */
	public void pinch(AndroidElement el) {
		driver.pinch(el);
	}

	/**
	 * 放大 (Zoom)： 放大屏幕 (双指往外移动来放大屏幕)
	 * 
	 * @param el
	 */
	public void zoom(AndroidElement el) {
		driver.zoom(el);
	}

	/**
	 * 滑动到 (Scroll To)： 滑动到某个元素
	 * 
	 * @param el
	 */
	public void scrollTo() {

		// WebElement element = driver.findElement(By.name("Element Name"));
		// HashMap<String, String> arguments = new HashMap<String, String>();
		// arguments.put("element", element.getId());
		// (JavascriptExecutor)driver.executeScript("mobile: scrollTo",
		// arguments);
	}

	/**
	 * 拉出文件 (Pull File) 从设备中拉出文件
	 * 
	 * @param remotePath
	 */
	public void pullFile(String remotePath) {
		driver.pullFile(remotePath);
	}

	/**
	 * 推送文件(Push file) 推送文件到设备中去
	 * 
	 * @param remotePath
	 */
	public void pushFile(String remotePath, String data) {
		byte[] base64Data = Base64.encodeBase64(data.getBytes());
		((AndroidDriver<AndroidElement>) driver).pushFile(remotePath, base64Data);
	}
}
