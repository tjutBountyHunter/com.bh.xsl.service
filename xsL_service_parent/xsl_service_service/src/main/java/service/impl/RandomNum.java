package service.impl;

/**
 * 生成随机数
 */
public class RandomNum {
    private String random = (int) ((Math.random() * 9 + 1) * 1000) + "";
    public static String num;

    public String getRandom() {
        return random;
    }
}
