package com.xl;


import base.BoxDriver;
import models.User;
import org.apache.commons.csv.CSVRecord;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import pages.WapPayPage;
import runner.TestngListener;
import utils.CsvUtility;
import utils.SeleniumUtils;
import utils.WaitUtil;


/**
 * Created by x on 2017/3/9.
 */
@Listeners({TestngListener.class})
public class PayWapSuperVIPTest {
    private BoxDriver baseDriver;
    private String baseUrl;
    private WapPayPage wapPayPage;
    private WebDriverWait wait;
    private long beginTime;
    private long endTime;
    private long totalPerTime;
   // private String path = "D:/test1";
   StringBuilder sb = new StringBuilder();



    @BeforeClass
    @Parameters({"driverType"})
    public void setUp(String driverType) {
        beginTime = System.currentTimeMillis();
        if (driverType.equals("firefox")) {

            //实例化浏览器驱动
            System.setProperty("webdriver.firefox.bin",//指定firefox的安装路径
                    "C:/Program Files (x86)/Mozilla Firefox/firefox.exe");
            String profile = "src/main/resources/firefoxProfile";
            this.baseDriver = new BoxDriver(",", profile, driverType);
            // this.baseDriver = new BoxDriver(",");
            this.baseUrl = "http://m.pay.xunlei.com";
            //清除浏览器cookies
            this.baseDriver.deleteCookies();
            this.wapPayPage = new WapPayPage(this.baseDriver, this.baseUrl);
        } else if (driverType.equals("ie")) {
            System.setProperty("webdriver.ie.driver", "C:/Program Files (x86/2345Soft/2345Explorer/2345Explorer.exe");
            this.baseDriver = new BoxDriver(",", "", "chrome");
            this.baseUrl = "http://m.pay.xunlei.com";
            //清除浏览器cookies
            this.baseDriver.deleteCookies();
            this.wapPayPage = new WapPayPage(this.baseDriver, this.baseUrl);

        } else if (driverType.equals("chrome")) {


            System.setProperty("webdriver.chrome.driver", "C:/Users/admin/AppData/Local/Google/Chrome/Application/chromedriver.exe");//本地
           // System.setProperty("webdriver.chrome.driver", "C:/Users/root1/AppData/Local/Google/Chrome/Application/chromedriver.exe");
           // System.setProperty("webdriver.chrome.driver",System.getProperty("chromedriver"));
           // System.setProperty("webdriver.chrome.driver", "C:/Users/Administrator/AppData/Local/Google/Chrome/Application/chromedriver.exe");//远程101
            System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");//chromedriver 解除硬编码
            this.baseDriver = new BoxDriver(",", "", "chrome");
            this.baseUrl = "http://m.pay.xunlei.com";
            //清除浏览器cookies
            this.baseDriver.deleteCookies();
            this.wapPayPage = new WapPayPage(this.baseDriver, this.baseUrl);
        }
    }

    @Test
    public void testLogin() throws Exception {
        BoxDriver driver = this.baseDriver;
        WapPayPage wapPayPage = this.wapPayPage;
        //先解析csv文件
        CsvUtility utility = new CsvUtility();
        Iterable<CSVRecord> csvData =
                utility.readCsvFile("src/main/resources/userList.csv");


        // 布尔型 true false
        boolean isFirstLine = true;
        // 循环每一个行，接下来根据每一行的值（数据），进行测试
        for (CSVRecord row : csvData) {
            if (isFirstLine) {
                isFirstLine = false;
                continue;
                // continue的作用
                // 当前循环到此为止，直接进入下一条循环
            }
            User user = new User();
            String username = row.get(0);
            System.out.println(username);
            String password = row.get(1);
            user.setUsername(username);
            user.setPassword(password);
            wapPayPage.openUrl();
            wapPayPage.login(user);
            WaitUtil.waitForLoad(driver.getWebDriver());
            Thread.sleep(2000);

            wapPayPage.chooseOutNumber(wapPayPage.superVIPOut);//选择超级会员
            Thread.sleep(4000);
            //  this.baseDriver.manageWaite();//千万不能加隐式等待，否则有空白
            WaitUtil.waitForLoad(driver.getWebDriver());//此行若注释也会出现空白图片
            Thread.sleep(4000);
            // this.baseDriver.manageWaite();
            SeleniumUtils.delAllFile(BoxDriver.ACTURAL_TMP_FOLDER_PATH);
            Thread.sleep(1000);


            this.baseDriver.saveScreen(driver.getWebDriver(), this.getClass().getSimpleName());//默认白金截图
            Thread.sleep(2000);
            this.baseDriver.scollBottom();//滑动滚动条以便来操作支付下单
            Thread.sleep(2000);
            WaitUtil.waitForLoad(driver.getWebDriver());
            Thread.sleep(2000);
            this.baseDriver.saveScreen(driver.getWebDriver(), this.getClass().getSimpleName());//点击下单前截图
            Thread.sleep(1000);
            //this.baseDriver.handleRadio(wapPayPage.payType, 10, 10);//选择支付宝*/

          /*  JavascriptExecutor js = (JavascriptExecutor) this.baseDriver.getWebDriver();//单独调试选择支付宝
            js.executeScript("arguments[0].click()", this.baseDriver.getWebDriver().findElement(By.xpath("/html/body/div[1]/div/div[3]/ul/li[2]")));*/

            //this.baseDriver.JavaScriptDisclick(wapPayPage.payType);//选择支付宝
            this.baseDriver.JavaScriptDisclick(wapPayPage.payType);//比较选择支付宝的js操作 必须用此js点击否则报响应超时
            // Thread.sleep(2000);
            // this.baseDriver.click("xpath,..");
            this.baseDriver.JavaScriptClick(wapPayPage.orderConfirm);//点击确认支付
            Thread.sleep(4000);
            //加入检查点，检查是否调到支付页面
/*
           /* WebElement webElement = this.baseDriver.locateElement(wapPayPage.titleText);
            System.out.println(webElement+"跳转到支付宝成功");*/

            this.baseDriver.back();
            //加入检查点，检查是否后退成功暂时不加断言
            // Thread.sleep(4000);
            WaitUtil.waitForLoad(driver.getWebDriver());
            Thread.sleep(4000);
            this.baseDriver.scollTop();
            Thread.sleep(4000);
            wapPayPage.chooseInNumber(wapPayPage.superVIPIn);//下单页面选择超级
            Thread.sleep(4000);
            WaitUtil.waitForLoad(driver.getWebDriver());
            Thread.sleep(4000);
            this.baseDriver.saveScreen(driver.getWebDriver(), this.getClass().getSimpleName());
            Thread.sleep(2000);
            this.baseDriver.scollBottom();//滑动滚动条以便来操作支付下单
            Thread.sleep(2000);
            WaitUtil.waitForLoad(driver.getWebDriver());
            Thread.sleep(2000);
            this.baseDriver.saveScreen(driver.getWebDriver(), this.getClass().getSimpleName());//点击下单前截图关于确认支付的金额
            Thread.sleep(2000);
            this.baseDriver.JavaScriptDisclick(wapPayPage.payType);//选择支付宝支付
            this.baseDriver.JavaScriptClick(wapPayPage.orderConfirm);//点击确认支付
            Thread.sleep(2000);
            driver.back();


        }
    }


    @AfterClass
    public void tearDown() {
        endTime = System.currentTimeMillis();
        totalPerTime = (endTime - beginTime) / 1000;
        System.out.println(this.getClass().getSimpleName() + "程序运行执行时间" + totalPerTime);
        this.baseDriver.quit();

    }
}
