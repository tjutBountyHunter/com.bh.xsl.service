package util;


import org.springframework.util.DigestUtils;

import java.io.UnsupportedEncodingException;

/**
 * @version V1.0
 * @ClassName:Md5Utils
 * @Description:悬赏令Md5Utils工具
 * @author:何林鸿
 * @date: 2018年7月31日
 */
public class Md5Utils {
    public static final String CHARSET_UTF8 = "UTF-8";

    public static String digestMds(final String data) {

        try {
            return DigestUtils.md5DigestAsHex(data.getBytes(CHARSET_UTF8));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Md5Util exception: data=" + data);
        }
    }
}
