/*** Eclipse Class Decompiler plugin, copyright (c) 2016 Chen Chao (cnfree2000@hotmail.com) ***/

package methods;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

public class ConfigurationMethods  {
	private WebDriver driver;
	public void printDesktopConfiguration() {
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
		Calendar cal = Calendar.getInstance();
		System.out.println("Following are machine configurations : \n");
		System.out.println((new StringBuilder("Date (MM/DD/YYYY) and Time (HH:MM:SS) : "))
				.append(dateFormat.format(cal.getTime())).toString());
		Capabilities cap = ((RemoteWebDriver) driver).getCapabilities();
		System.out.println((new StringBuilder("Browser : ")).append(cap.getBrowserName()).toString());
		System.out.println((new StringBuilder("Platform : ")).append(cap.getPlatform()).toString());
	}
	public ConfigurationMethods(WebDriver driver) {
		super();
		this.driver = driver;
	}
	
}