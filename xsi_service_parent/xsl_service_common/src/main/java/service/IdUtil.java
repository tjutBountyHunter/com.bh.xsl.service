package service;

import java.text.SimpleDateFormat;
import java.util.Random;

public class IdUtil {
    private static int id = 327;

    /**
     * 图片名生成
     */
    public static String genImageName() {
        //取当前时间的长整形值包含毫秒
        long millis = System.currentTimeMillis();
        //long millis = System.nanoTime();
        //加上三位随机数
        Random random = new Random();
        int end3 = random.nextInt(999);
        //如果不足三位前面补0
        String str = millis + String.format("%03d", end3);

        return str;
    }

    /**
     * id生成
     */
    public static int getId() {
        id += 1;
        long now = System.nanoTime();
        SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy");
        String time = dataFormat.format(now);
        String info = now + "";
        int run = 0;
        if (id > 999) {
            id = 100;
        }
        run = id;
        String q = time + run + info.substring(info.length() - 4, info.length());
        //return Long.parseLong(q);
        return 0;
    }
}
