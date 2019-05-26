package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author 梁俊伟
 * @version 1.0
 * @date 2019/4/20 23:32
 */
public class DateUtil {

    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 将string类型的时间转化为Date类型的
     * @param stringDate
     * @return
     */
    public static synchronized Date stringToDate(String stringDate){
        try {
            Date date = dateFormat.parse(stringDate);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static synchronized String dateToString(Date date){
        String dateString = dateFormat.format(date);
        return dateString;
    }
}
