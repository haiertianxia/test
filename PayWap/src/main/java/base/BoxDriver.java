package base;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Created by x on 2017/3/11.
 */
public class BoxDriver {
    public static final String FAIL_TMP_FOLDER_PATH = "failTmp";//失败截图保存
    public static final File FAIl_TMP_FOLDER;
    public static final String ACTURAL_TMP_FOLDER_PATH = "actualtmp";//实际拍照保存
    public static final File ACTURAL_TMP_FOLDER;

    public static final File  EXPECTL_TMP_FOLDER_FOLDER; //本地标准图片文件夹
    public static final File  EXPECTE_TMP_FOLDER_FOLDER;//101
    public static final File  EXPECT_TMP_FOLDER_FOLDER;//20

    public static final String EXPECTL_TMP_FOLDER_PATH = "expectLocal";//本地标准图片存在的位置
    public static final String EXPECTE_TMP_FOLDER_PATH = "expectExcise";//192.168.27.101存放的标准图片位置
    public static final String EXPECT_TMP_FOLDER_PATH = "expectFinal";//10.10.45.20存放标准图片

    public static int i;

    static {
        FAIl_TMP_FOLDER = new File(FAIL_TMP_FOLDER_PATH);
        if (FAIl_TMP_FOLDER.exists()) {
            FAIl_TMP_FOLDER.mkdirs();
        }
        ACTURAL_TMP_FOLDER = new File(ACTURAL_TMP_FOLDER_PATH);
        if (ACTURAL_TMP_FOLDER.exists()) {
            ACTURAL_TMP_FOLDER.mkdirs();
        }
        EXPECTL_TMP_FOLDER_FOLDER = new File(EXPECTL_TMP_FOLDER_PATH);
        if (EXPECTL_TMP_FOLDER_FOLDER.exists()) {
            EXPECTL_TMP_FOLDER_FOLDER.mkdirs();
        }

        EXPECTE_TMP_FOLDER_FOLDER = new File(EXPECTE_TMP_FOLDER_PATH);
        if (EXPECTE_TMP_FOLDER_FOLDER.exists()) {
            EXPECTE_TMP_FOLDER_FOLDER.mkdirs();
        }
        EXPECT_TMP_FOLDER_FOLDER = new File(EXPECT_TMP_FOLDER_PATH);
        if (EXPECT_TMP_FOLDER_FOLDER.exists()) {
            EXPECT_TMP_FOLDER_FOLDER.mkdirs();
        }
    }

    static WebDriver baseDriver;
    String byChar;
    WebDriverWait wait;

    public BoxDriver(String byChar, String profile, String driverTpye) {

        FirefoxProfile ffp = null;
        //如果有传递 firefox profile 的路径
        if (!profile.equals("")) {
            // 用 路径 实例化一个 FirefoxProfile
            File f = new File(profile);
            ffp = new FirefoxProfile(f);
            this.baseDriver = new FirefoxDriver(ffp);
            this.byChar = byChar;
        } else if (driverTpye.equals("chrome")) {

            Map<String, String> mobileEmulation = new HashMap<String, String>();
            mobileEmulation.put("deviceName", "Google Nexus 5");
            Map<String, Object> chromeOptions = new HashMap<String, Object>();
            // chromeOptions.put("mobileEmulation", mobileEmulation);
            DesiredCapabilities capabilities = DesiredCapabilities.chrome();
            capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
            this.baseDriver = new ChromeDriver(capabilities);
            this.byChar = byChar;

        } else {

            this.baseDriver = new InternetExplorerDriver();
            this.byChar = byChar;


        }


    }

    public WebDriver getWebDriver() {
        return baseDriver;
    }
    /*public BoxDriver(String byChar, String driverTpye) {
        switch (driverTpye) {
            case "firefox":
                this.baseDriver = new FirefoxDriver();
                this.byChar = byChar;
                break;
        }
        switch (driverTpye) {
            case "chrome":
                Map<String, String> mobileEmulation = new HashMap<String, String>();
                mobileEmulation.put("deviceName", "Google Nexus 5");
                Map<String, Object> chromeOptions = new HashMap<String, Object>();
                // chromeOptions.put("mobileEmulation", mobileEmulation);
                DesiredCapabilities capabilities = DesiredCapabilities.chrome();
                capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
                this.baseDriver = new ChromeDriver(capabilities);
                this.byChar = byChar;
                break;
        }
        switch (driverTpye) {
            case "ie":
                this.baseDriver = new InternetExplorerDriver();
                this.byChar = byChar;
        }
    }*/

    public WebElement locateElement(String selector) {
        WebElement we;
        // 如果定位符中 有 分隔符，那么就从分隔符处分成两段
        // 第一段是By
        // 第二段是真正的定位符
        // 如果没有分隔符，就默认用 id 定位
        if (!selector.contains(this.byChar)) {
            // 用 id 定位
            we = this.baseDriver.findElement(By.id(selector));
        } else {
            // 用 分隔符 分成两个部分
            String by = selector.split(this.byChar)[0];
            String value = selector.split(this.byChar)[1];
            we = findElementByChar(by, value);
        }
        return we;
    }

    public By locateByType(String selector) {
        if (!selector.contains(this.byChar)) {
            return By.id(selector);
        } else {
            String by = selector.split(this.byChar)[0];
            String value = selector.split(this.byChar)[1];
            if (by.equals("name")) {

                return By.name(selector);
            } else if (by.equals("class")) {
                return By.className(selector);
            } else if (by.equals("css")) {
                return By.cssSelector(selector);
            } else if (by.equals("xpath")) {
                return By.xpath(selector);
            } else if (by.equals("link")) {
                return By.linkText(selector);
            } else {//partial
                return By.partialLinkText(selector);
            }

        }

    }




   /* public WebDriver getWebDriver() {
        return baseDriver;
    }*/

    /**
     * 根据具体的 by 和 value，进行元素定位，并返回该元素
     *
     * @param by
     * @param value
     * @return
     */
    private WebElement findElementByChar(String by, String value) {
        WebElement we = null;
        if (by.toLowerCase().equals("id")) {

            we = this.baseDriver.findElement(By.id(value));
        } else if (by.toLowerCase().equals("css")) {
            we = this.baseDriver.findElement(By.cssSelector(value));
        } else if (by.toLowerCase().equals("xpath")) {
            we = this.baseDriver.findElement(By.xpath(value));
        } else if (by.toLowerCase().equals("link")) {
            we = this.baseDriver.findElement(By.linkText(value));
        } else if (by.toLowerCase().equals("class")) {
            we = this.baseDriver.findElement(By.className(value));
        } else if (by.toLowerCase().equals("link")) {
            we = this.baseDriver.findElement(By.linkText(value));
        }

        return we;
    }

    public void click(String bachar) {
        WebDriverWait wait = new WebDriverWait(this.baseDriver, 50);
        wait.until(ExpectedConditions.presenceOfElementLocated(this.locateByType(bachar)));
        this.locateElement(bachar).click();
    }

    public void switchTo(String bachar) {
        WebDriverWait wait = new WebDriverWait(this.baseDriver, 50);
        wait.until(ExpectedConditions.presenceOfElementLocated(this.locateByType(bachar)));
        this.baseDriver.switchTo().frame(this.locateElement(bachar));

    }

    public void sendKeys(String bachar, String value) {
        this.locateElement(bachar).sendKeys(value);
        WebDriverWait wait = new WebDriverWait(this.baseDriver, 20);
        wait.until(ExpectedConditions.presenceOfElementLocated(this.locateByType(bachar)));
    }

    public void switchDefault() {
        this.baseDriver.switchTo().defaultContent();
    }

    public void getUrl(String url) {
        this.baseDriver.get(url);

    }

    public void maxiSize() {
        this.baseDriver.manage().window().maximize();
    }

    public void deleteCookies() {
        this.baseDriver.manage().deleteAllCookies();
    }

    public void back() {
        this.baseDriver.navigate().back();

    }

    public void forward() {
        this.baseDriver.navigate().back();
    }

    public void getWindowHandle() {


        String strMainHandler = this.baseDriver.getWindowHandle();
        this.baseDriver.switchTo().window(strMainHandler);
    }


    public void JavaScriptClick(String byChar) throws Exception {
        WebDriverWait wait = new WebDriverWait(this.baseDriver, 90);
        wait.until(ExpectedConditions.presenceOfElementLocated(this.locateByType(byChar)));
        try {
            WebElement element = this.locateElement(byChar);
            /*
             * if条件判断函数参数传入的element元素是否处于可单击状态，以及是否显示在页面上
             */
            if (element.isEnabled() && element.isDisplayed()) {
                //执行JavaScript语句arguments[0].click();
                ((JavascriptExecutor) this.baseDriver).executeScript("arguments[0].click()",
                        element);
            } else {
                System.out.println("页面上的元素无法进行单击操作");
            }

           /* ((JavascriptExecutor) this.baseDriver).executeScript("arguments[0].click()",
                    element);*/
            //当出现异常的时候，catch语句会被执行，打印相关的异常信息和出错的堆栈信息
        } catch (StaleElementReferenceException e) {
            System.out.println("页面元素没有附加在网页中" + e.getStackTrace());
        } catch (NoSuchElementException e) {
            System.out.println("页面元素没有找到要操作的页面元素" + e.getStackTrace());
        } catch (Exception e) {
            System.out.println("无法完成单击操作" + e.getStackTrace());
        }

    }

    //针对元素不可点击和元素没显示到页面，直接js点击
    public void JavaScriptDisclick(String byChar) throws Exception {
        WebElement element = this.locateElement(byChar);
        ((JavascriptExecutor) this.baseDriver).executeScript("arguments[0].click()",
                element);
    }

    public void manageWaite() {
        this.baseDriver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
    }

    /**
     * 滑动滚动条到底部
     */

    public void scollBottom() {
        ((JavascriptExecutor) this.baseDriver).executeScript("window.scrollTo(0, document.body.scrollHeight)");

    }

    /**
     * 滑动滚动条到顶部
     */

    public void scollTop() {
       // ((JavascriptExecutor) this.baseDriver).executeScript("window.scrollTo(Math.max(document.documentElement.scrollHeight,document.body.scrollHeight,document.documentElement.clientHeight),0");
        String js="var q=document.documentElement.scrollTop=0";
        ((JavascriptExecutor) this.baseDriver).executeScript(js);

        //((JavascriptExecutor) this.baseDriver).executeScript("document.documentElement.scrollTop=0");
    }

    //鼠标左键
    public void clickLeft(String bachar) {
        WebDriverWait wait = new WebDriverWait(this.baseDriver, 50);
        Actions action = new Actions(this.baseDriver);
        action.click();
        action.click(this.locateElement(bachar));
        wait.until(ExpectedConditions.presenceOfElementLocated(this.locateByType(byChar)));
    }

    public void hoverAndClick(String selector) {
        WebDriverWait wait = new WebDriverWait(this.baseDriver, 50);
        //SystemUtil.moveMouse(0, 0);
        Actions action = new Actions(this.baseDriver);
        WebElement mainMenu = this.locateElement(selector);
        // action.moveByOffset(0,0);
        // action.moveToElement(mainMenu).doubleClick(this.baseDriver.findElement(main)).build().perform();
        action.moveToElement(mainMenu).doubleClick(this.locateElement(selector)).build().perform();
        wait.until(ExpectedConditions.presenceOfElementLocated(this.locateByType(selector)));


    }


    //判断页面是否加载完成暂不用dfdsfsdf
   /* public Boolean isDoaload(String selector) {
        Boolean elag = false;
        if (this.locateElement(selector).getText().equals("test0015(奋发向上)")) {

            elag = true;

        }
        return elag;
    }*/

    //程序运行过程中截图
    public void saveScreen(WebDriver driver, Object common) {

        String prefix = BoxDriver.ACTURAL_TMP_FOLDER_PATH;
         //String prefix = BoxDriver.EXPECT_TMP_FOLDER_PATH;

        try {

            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            Date date = new Date();
            //Long time = date.getTime();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHMMss");
            String dateTime = sdf.format(date);
            //文件命名

            i++;

            File file = new File(prefix + File.separator + i + "-" + dateTime + "&" + common.toString() + ".png");


            File tempDir = new File(prefix);
            //判断根目录文件夹是否存在
            if (!tempDir.exists() || !tempDir.isDirectory()) {
                tempDir.mkdir();
            } else {
                int j = 1;
                while (file.exists()) {
                    //命名重复就后缀加_1
                    file = new File(prefix + File.separator + dateTime + "&" + common.toString() + "_" + j + ".png");
                }
            }


            FileUtils.copyFile(screenshot, file);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //操作单选框
    public void handleRadio(String selector, int x, int y) {
        WebElement radioEle = this.locateElement(selector);
        Actions action = new Actions(this.baseDriver);
        action.moveToElement(radioEle, x, y);

    }

    //获取当前url
    public void getHref() {
        String currentUrl = this.getWebDriver().getCurrentUrl();
    }

    public void quit() {
        this.baseDriver.quit();
    }

    //失败截图
    public static void takeScreenShot(ITestResult tr) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
        String mDateTime = formatter.format(new Date());
        String fileName = mDateTime + "_" + tr.getName() + ".png";
        File screenshot = ((TakesScreenshot) baseDriver).getScreenshotAs(OutputType.FILE);
        File file = new File(FAIl_TMP_FOLDER, fileName);
        try {
            FileUtils.copyFile(screenshot, file);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}