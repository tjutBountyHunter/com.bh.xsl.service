package util;

import java.util.List;
import java.util.Random;

public class ImageFile {
    private static String[] image = {"http://47.93.200.190/images/20181028003802.png", "http://47.93.200.190/images/20181028003801.png",
            "http://47.93.200.190/images/20181028003803.png", "http://47.93.200.190/images/20181028003804.jpg", "http://47.93.200.190/images/20181028003805.jpg"};

    public static String fileName() {
        Random rd = new Random();
        int random = rd.nextInt(image.length);
        String writere = image[random];
        return writere;
    }

    public static void main(String[] args) {
        System.out.println(fileName());
    }
}
