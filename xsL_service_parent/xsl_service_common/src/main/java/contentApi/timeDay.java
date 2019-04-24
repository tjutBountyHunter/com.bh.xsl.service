package contentApi;

import java.text.SimpleDateFormat;
import java.util.Date;

public class timeDay {
    public static String timeDay() {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("HHmmss");
        String str = dateFormat.format(date);
        return str;
    }
}
