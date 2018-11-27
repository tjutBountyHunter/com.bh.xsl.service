package contentApi;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import pojo.IbmCommercial;
import service.XslResult;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;

public class createImage {
    public static XslResult createImCode(String contents, IbmCommercial ibmCommercial) {
        MultiFormatWriter writer = new MultiFormatWriter();
        int w = 300;
        int h = 300;
        HashMap<EncodeHintType, Object> hints = new HashMap<>();
//        //容错机制
//        hints.put(EncodeHintType.ERROR_CORRECTION,30);
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        try {
            //返回二进制矩阵数组
            BitMatrix bitMatrix = writer.encode(contents, BarcodeFormat.QR_CODE, w, h, hints);
            //创建图片
            BufferedImage qr = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
            for (int x = 0; x < w; x++) {
                for (int y = 0; y < h; y++) {
                    if (bitMatrix.get(x, y)) {
                        qr.setRGB(x, y, 0xFF0000);
                    } else {
                        qr.setRGB(x, y, 0xffffff);
                    }
                }
            }
            //读取logo
            String s = "/home/ftp/www/images/" + ibmCommercial.getUserid() + ".png";
            System.out.println(s);
            BufferedImage logoImg = ImageIO.read(new File(s));
            int logoW = 30;
            int logoH = 30;
            Image logoUse = logoImg.getScaledInstance(30, 30, Image.SCALE_FAST);
//            绘制logo
//            获取画笔
            Graphics graphics = qr.getGraphics();
            int logoX = (w - logoW) / 2;
            int logoY = (w - logoH) / 2;
            graphics.drawImage(logoUse, logoX, logoY, null);
            String s2 = "/home/ftp/www/images/" + ibmCommercial.getCommercialcode() + ".png";
            ImageIO.write(qr, "png", new File(s2));
            return XslResult.ok("http://47.93.200.190/images/" + ibmCommercial.getCommercialcode() + ".png");
        } catch (Exception e) {
            e.printStackTrace();
            return XslResult.build(500, "服务器异常");
        }
    }
}
