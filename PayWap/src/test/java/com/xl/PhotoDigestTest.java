package com.xl;

import base.BoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.SeleniumUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.*;

/**
 * Created by admin on 2017/3/21.
 */
public class PhotoDigestTest {

    @Test
    public void testComPareImage() throws UnknownHostException {

        if(InetAddress.getLocalHost().getHostAddress().equals("192.168.25.133")){
            //String p = System.getProperty("chromedriver");
            //System.out.println(System.getProperty("chromedriver"));

            //  System.out.println(System.setProperty("webdriver.chrome.driver",System.getenv("chromedriver"));

            //compareImage("D:/test/SuperTest20170321170326.png", "D:/testExpect/SuperTest20170321170326.png");//用来单个测试
            // listFileName("D:/test/");
            // listFileName("D:/testExpect");
            //compareImage(listFileName("D:/test/"),listFileName("D:/testExpect/ile[] arras = SeleniumUtils.sort(listFiles(BoxSeleiniumUtils.Driver.ACTURAL_TMP_FOLDER_PATH));SeleniumUtils.
            File[] arras = SeleniumUtils.sort(SeleniumUtils.listFiles(BoxDriver.ACTURAL_TMP_FOLDER_PATH));
            java.util.List<File> afiles = Arrays.asList(arras);
            File[] expects = SeleniumUtils.sort(SeleniumUtils.listFiles(BoxDriver.EXPECTL_TMP_FOLDER_PATH));
            java.util.List<File> efiles = Arrays.asList(expects);

        /*Object[] acturals = actural.toArray();
        Object[] expects = expect.toArray();*/

       /* System.out.println(afiles + "111");
        System.out.println(efiles + "222");*/

            System.out.println("实际个数" + afiles.size());
            for (int i = 0; i < afiles.size(); i++) {
                // System.out.println("实际图片s-" + afiles.get(i));
                //System.out.println("期望图片e-" + efiles.get(i));
                // System.out.println("测试"+BoxDriver.EXPECT_TMP_FOLDER_PATH + expect.get(i));

                String path1 = afiles.get(i).getPath();

                // System.out.println("实际图片sp-" + path1);
                String path2 = efiles.get(i).getPath();
                //  System.out.println("期望图片ep-" + path2);
                try {

                    SeleniumUtils.compareImage(path1, path2);

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

        }else if(InetAddress.getLocalHost().getHostAddress().equals("192.168.27.101")){
            File[] arras = SeleniumUtils.sort(SeleniumUtils.listFiles(BoxDriver.ACTURAL_TMP_FOLDER_PATH));
            java.util.List<File> afiles = Arrays.asList(arras);
            File[] expects = SeleniumUtils.sort(SeleniumUtils.listFiles(BoxDriver.EXPECTE_TMP_FOLDER_PATH));
            java.util.List<File> efiles = Arrays.asList(expects);

        /*Object[] acturals = actural.toArray();
        Object[] expects = expect.toArray();*/

       /* System.out.println(afiles + "111");
        System.out.println(efiles + "222");*/

            System.out.println("实际个数" + afiles.size());
            for (int i = 0; i < afiles.size(); i++) {
                // System.out.println("实际图片s-" + afiles.get(i));
                //System.out.println("期望图片e-" + efiles.get(i));
                // System.out.println("测试"+BoxDriver.EXPECT_TMP_FOLDER_PATH + expect.get(i));

                String path1 = afiles.get(i).getPath();

                // System.out.println("实际图片sp-" + path1);
                String path2 = efiles.get(i).getPath();
                //  System.out.println("期望图片ep-" + path2);
                try {

                    SeleniumUtils.compareImage(path1, path2);

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }


        }else{
            File[] arras = SeleniumUtils.sort(SeleniumUtils.listFiles(BoxDriver.ACTURAL_TMP_FOLDER_PATH));
            java.util.List<File> afiles = Arrays.asList(arras);
            File[] expects = SeleniumUtils.sort(SeleniumUtils.listFiles(BoxDriver.EXPECT_TMP_FOLDER_PATH));
            java.util.List<File> efiles = Arrays.asList(expects);

        /*Object[] acturals = actural.toArray();
        Object[] expects = expect.toArray();*/

       /* System.out.println(afiles + "111");
        System.out.println(efiles + "222");*/

            System.out.println("实际个数" + afiles.size());
            for (int i = 0; i < afiles.size(); i++) {
                // System.out.println("实际图片s-" + afiles.get(i));
                //System.out.println("期望图片e-" + efiles.get(i));
                // System.out.println("测试"+BoxDriver.EXPECT_TMP_FOLDER_PATH + expect.get(i));

                String path1 = afiles.get(i).getPath();

                // System.out.println("实际图片sp-" + path1);
                String path2 = efiles.get(i).getPath();
                //  System.out.println("期望图片ep-" + path2);
                try {

                    SeleniumUtils.compareImage(path1, path2);

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

        }
        }



}




