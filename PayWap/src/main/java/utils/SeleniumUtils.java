package utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;

/**
 * Created by x on 2017/3/11.
 */
public class SeleniumUtils {
    public static void saveScreen(WebDriver driver, Object common) throws IOException {
        //暂不用，已经截图方法放到BoxDriver
       /* String prefix = "D:/test1";
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        Date date = new Date();
        Long time = date.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHMMss");
        String dateTime = sdf.format(time);
        //文件命名
        File file = new File(prefix + File.separator + common.toString().substring(6) + dateTime + ".png");
        System.out.println(time);

        File tempDir = new File(prefix);
        //判断根目录文件夹是否存在
        if (!tempDir.exists() || !tempDir.isDirectory()) {
            tempDir.mkdir();
        } else {
            int i = 1;
            while (file.exists()) {
                //命名重复就后缀加_1
                file = new File(prefix + File.separator + common.toString().substring(6) + dateTime + "_" + i + ".png");
            }
        }
        FileUtils.copyFile(screenshot, file);*/
    }


    //删除文件夹
//param folderPath 文件夹完整绝对路径

    public static void delFolder(String folderPath) {
        try {
            delAllFile(folderPath); //删除完里面所有内容
            String filePath = folderPath;
            filePath = filePath.toString();
            java.io.File myFilePath = new java.io.File(filePath);
            myFilePath.delete(); //删除空文件夹
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //删除指定文件夹下所有文件
//param path 文件夹完整绝对路径
    public static boolean delAllFile(String path) {
        boolean flag = false;
        File file = new File(path);
        if (!file.exists()) {
            return flag;
        }
        if (!file.isDirectory()) {
            return flag;
        }
        String[] tempList = file.list();
        File temp = null;
        for (int i = 0; i < tempList.length; i++) {
            if (path.endsWith(File.separator)) {
                temp = new File(path + tempList[i]);
            } else {
                temp = new File(path + File.separator + tempList[i]);
            }
            if (temp.isFile()) {
                temp.delete();
            }
            if (temp.isDirectory()) {
                delAllFile(path + "/" + tempList[i]);//先删除文件夹里面的文件
                //  delFolder(path + "/" + tempList[i]);//再删除空文件夹
                flag = true;
            }
        }
        return flag;
    }


    //查询匹配文件名删除
    public static void judgeDelete(String path, Object common) {
        File folder = new File(path);
        File[] files = folder.listFiles();
        for (File file : files) {
            //当前文件夹图片的名字是否包含该类的名字
            /*if (file.getName().substring(file.getName().indexOf("&"), file.getName().indexOf("-")).equals(common.toString())) {
                System.out.println("删除方法前" + file.getName());
                file.delete();
            }*/
            if (file.getName().substring((file.getName().indexOf("&")+1),file.getName().indexOf(".")).equals(common.toString())) {
              //  System.out.println("删除方法前" + file.getName());
                file.delete();
            }

          /*  System.out.println("删除方法后" + file.getName());
            System.out.println("测试截取字符串" + file.getName().substring((file.getName().indexOf("&")+1),file.getName().indexOf(".")));
            System.out.println("类名" + common.toString());*/
        }
    }
    //判断文件内容是否为空暂不用
    public static Boolean isEmpty(String path) {
        Boolean flag = false;
        File file = new File(path);
        if (file.length() == 0) {
           // System.out.println("文件为空！");
            flag = true;
        }
        return flag;
    }


    /**
     * 图片对比  实际图片和标准图片
     * @param actualPage
     * @param expectPage
     * @throws Exception
     */
    public static  void compareImage(String actualPage, String expectPage) throws Exception {
        java.text.NumberFormat percentFormat = java.text.NumberFormat.getPercentInstance();
        percentFormat.setMaximumFractionDigits(2); //最大小数位数
        // float percent = compare(getData(actualPage), getData(expectPage));
        String percent = percentFormat.format(compare(getData(actualPage), getData(expectPage)));//自动转换成百分比显示..
        System.out.println(actualPage.substring(actualPage.lastIndexOf("/") + 1) + "和" + expectPage.substring(expectPage.lastIndexOf("/") + 1) + "两张图片的相似度为：" + percent);
        Assert.assertTrue(percent.equals("100%"));
    }


    public static  int[] getData(String name) throws Exception {
        BufferedImage img = ImageIO.read(new File(name));
        BufferedImage slt = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
        slt.getGraphics().drawImage(img, 0, 0, 100, 100, null);
        //ImageIO.write(slt,"jpeg",new File("slt.jpg"));
        int[] data = new int[256];
        for (int x = 0; x < slt.getWidth(); x++) {
            for (int y = 0; y < slt.getHeight(); y++) {
                int rgb = slt.getRGB(x, y);
                Color myColor = new Color(rgb);
                int r = myColor.getRed();
                int g = myColor.getGreen();
                int b = myColor.getBlue();
                data[(r + g + b) / 3]++;
            }
        }
        //data 就是所谓图形学当中的直方图的概念
        return data;
    }

    public static float compare(int[] s, int[] t) {
        float result = 0F;
        for (int i = 0; i < 256; i++) {
            int abs = Math.abs(s[i] - t[i]);
            int max = Math.max(s[i], t[i]);
            result += (1 - ((float) abs / (max == 0 ? 1 : max)));
        }
        return (result / 256);
    }

    /**
     * 列出文件
     * @param path
     * @return 返回list
     *
     */
    /*public static ArrayList listFileName(String path) {
        String[] filenames = new File(path).list();
        ArrayList arrlist = new ArrayList();

        String photoPer = null;
        for (String filename : filenames) {


            arrlist.add(filename);


        }

        System.out.println(arrlist);
        return arrlist;
    }*/


    /**
     *  列出文件
     * @param path
     * @return  返回文件file[]
     */
    public static File[]  listFiles(String path){
        File[] files = new File(path).listFiles();
        return  files;
    }

    /**
     * 排序文件
     * @param t
     * @return 返回file[]
     */
    public  static File[] sort(File[] t) {
        Arrays.sort(t, new Comparator<File>() {
            public int compare(File f1, File f2) {
                String s1 = f1.getName();
                String s2 = f2.getName();
                s1 = s1.substring(0, s1.indexOf('-'));//去除后缀找文件名数字开头
                s2 = s2.substring(0, s2.indexOf('-'));
                return Integer.parseInt(s1) - Integer.parseInt(s2);
            }
        });
        return  t;
    }

    /*public ArrayList array(ArrayList list) {
        Comparator<String> comparator = new Comparator<String>() {

            @Override
            public int compare(String o1, String o2) {
                Integer i1 = Integer.parseInt(o1.substring(0, o1.indexOf("-")));
                Integer i2 = Integer.parseInt(o2.substring(0, o2.indexOf("-")));
                return i1 > i2 ? 1 : -1;
            }
        };
        return list;

    }*/
}

