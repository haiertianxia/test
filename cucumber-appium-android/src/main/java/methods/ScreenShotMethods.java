/*** Eclipse Class Decompiler plugin, copyright (c) 2016 Chen Chao (cnfree2000@hotmail.com) ***/

package methods;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenShotMethods {
	private WebDriver driver;
	public void takeScreenShot() throws IOException {
		File scrFile = (File) ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		Calendar cal = Calendar.getInstance();
		System.out.println(dateFormat.format(cal.getTime()));
		String scrFilepath = scrFile.getAbsolutePath();
		System.out.println((new StringBuilder("scrFilepath: ")).append(scrFilepath).toString());
		File currentDirFile = new File("Screenshots");
		String path = currentDirFile.getAbsolutePath();
		System.out.println((new StringBuilder("path: ")).append(path).append("+++").toString());
		System.out.println((new StringBuilder("****\n")).append(path).append("\\screenshot")
				.append(dateFormat.format(cal.getTime())).append(".png").toString());
		FileUtils.copyFile(scrFile, new File((new StringBuilder(String.valueOf(path))).append("\\screenshot")
				.append(dateFormat.format(cal.getTime())).append(".png").toString()));
	}
	public ScreenShotMethods(WebDriver driver) {
		super();
		this.driver = driver;
	}
	
}