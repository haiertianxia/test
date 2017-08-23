package utils;


import org.testng.annotations.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by admin on 2017/3/21.
 */
/*
public class TimeUtil {
    public static Date getDefTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Date time = null;
        try {
            time = sdf.parse(sdf.format(new Date()));

        } catch (ParseException e) {

            e.printStackTrace();
        }
        return time;
    }

    //单独测试时间
    @Test
    public void calTime() throws InterruptedException {
        long l1 = System.currentTimeMillis();
        Thread.sleep(3000);
        long l2 = System.currentTimeMillis();
        long l3 = l2 - l1;
        System.out.println(l3);
        System.out.println(l1);
        System.out.println(l2);
    }

}
*/
