package pages;

import base.BoxDriver;
import models.User;
import utils.WaitUtil;


/**
 * Created by x on 2017/3/9.
 */
public class WapPayPage {
    private BoxDriver baseDriver;
    private String baseUrl;
    private String LOGINBUTTON = "css,body > div > div > div.pri_user > div.pri_opt > a";
    public String superVIPOut = "css,body > div > div > div.pri_list > ul > li:nth-child(1) > a";//超级会员的点击按钮
    //final String superVIPOut = "css,a[data-clickid='supervip']";//超级会员的点击按钮

    public String logout = "class,btn_chg";
    public String superVIPIUrl = "http://m.pay.xunlei.com/vippay/pay.html?bizNo=supervip";//超级会员跳转url
    public String baiJinOut = "css,body > div > div > div.pri_list > ul > li:nth-child(2) > a";//白金会员
    public String quickBirdOut = "css,body > div > div > div.pri_list > ul > li:nth-child(3) > a";//迅雷快鸟会员
    public String gameOut = "css,body > div > div > div.pri_list > ul > li:nth-child(4) > a";//普通网游加速会员
    public String highGameOut = "css,body > div > div > div.pri_list > ul > li:nth-child(5) > a";//高级网游加速会员
    public String WAPLOGINFRAME = "loginIframe";
    private String wapLonginId = "al_u";
    private String wapPassWordId = "al_p";
    private String wapSubmitId = "al_submit";
    public String baijinIn = "css,body > div.wrap > div > div.pay_main > div.pay_type > ul > li.cur > span";//白金pay
    public String superVIPIn = "css,body > div.wrap > div > div.pay_main > div.pay_type > ul > li:nth-child(2)";//超级pay
    //public final String quickBirdIn = "x,/html/body/div[1]/div/div[2]/div[1]/ul/li[1]";//快鸟pay
    public String quickBirdIn = "css,body > div.wrap > div > div.pay_main > div.pay_type > ul > li:nth-child(1)";

    public String gameIn = "css,body > div.wrap > div > div.pay_main > div.pay_type > ul > li:nth-child(1)";//普通网游pay
    public String highGameIn = "css,body > div.wrap > div > div.pay_main > div.pay_type > ul > li.cur > span";//高级网游pay

    // public String nickname = "css,body > div.wrap > div > div.info > div > p > span";//昵称
    //public String payType = "xpath,/html/body/div[1]/div/div[3]/ul/li[2]";//选择支付宝支付按钮
    public String payType = "css,body > div.wrap > div > div.pay_way > ul > li:nth-child(2)";//选择支付宝支付按钮
    //public String payType = "xpath,//li[@class='cho']";
    public String orderConfirm = "css,body > div.wrap > div > div.sumbit > a";//确认支付
    // public String titleText = " css，body > header > h1 > span";//登录支付宝



    public WapPayPage(BoxDriver driver, String baseUrl) {
        this.baseDriver = driver;
        this.baseUrl = baseUrl;
    }

    public void login(User user) throws Exception {
        BoxDriver driver = this.baseDriver;
        driver.JavaScriptClick(LOGINBUTTON);
        driver.switchTo(WAPLOGINFRAME);
        //driver.manageWaite();
        WaitUtil.waitForLoad(driver.getWebDriver());
        Thread.sleep(3000);
        driver.sendKeys(wapLonginId, user.getUsername());
        driver.sendKeys(wapPassWordId, user.getPassword());
        driver.JavaScriptClick(wapSubmitId);
        driver.switchDefault();


    }

    public void openUrl() throws InterruptedException {
        BoxDriver driver = this.baseDriver;
        driver.getUrl(this.baseUrl);
        driver.maxiSize();



    }

    public void chooseOutNumber(String typeOutNumber) throws Exception {
        BoxDriver driver = this.baseDriver;
        driver.JavaScriptClick(typeOutNumber);


    }

    public void chooseInNumber(String typeInNumber) throws Exception {
        BoxDriver driver = this.baseDriver;
        System.out.print(driver);
        driver.JavaScriptClick(typeInNumber);


    }

  /*  public void chooseOrtherInNumber(String typeInNumber) throws Exception {
        BoxDriver driver = this.baseDriver;
        System.out.print(driver);
        driver.hoverAndClick(typeInNumber);


    }*/
}

