package util;

import static java.lang.Integer.decode;

public class StringTransitionInt {
    public static Integer transitionInt(String json) {
        Integer integer = decode(json);
        return integer;
    }

    public static String transitionString(Integer integer) {
        String json = integer + "";
        return json;
    }
}
