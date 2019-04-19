package util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class TimeDemo extends Thread {
    /**
     * @param args
     */
    public static void main(String[] args) {
        timer();

    }

    public static void timer() {
        long timestamp = System.currentTimeMillis();
        Date date = new Date(timestamp);
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                //currentTimeMillis返回以毫秒为单位的当前时间。
                long timestamp = System.currentTimeMillis();
                Date now = new Date(timestamp);
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                //format要被格式化为日期-时间字符串的日期-时间值。
                System.out.println(df.format(now));
            }
        }, 0, 1000);
    }
}
