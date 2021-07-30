package tech.dongkaiyang.util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Random;

/**
 * @ClassName ImageUtil
 * @Description
 * @Author zyx
 * @Date 2021-07-30 8:50
 * @Blog www.winkelblog.top
 */


public class ImageUtil {

    final static String s = "ABCDEFGHJKLMNPRSTUVWXYZ0123456789";

    /**
     * 产生一张验证码
     *
     * @param output
     * @return
     */
    public static String drawImage(ByteArrayOutputStream output) {
        StringBuilder code = new StringBuilder();
        // 随机产生4个字符
        for (int i = 0; i < 4; i++) {
            code.append(randomChar());
        }
        int width = 70;
        int height = 27;
        BufferedImage bi = new BufferedImage(width, height,
                BufferedImage.TYPE_3BYTE_BGR);
        Font font = new Font("Times New Roman", Font.ITALIC, 20);
        // 调用Graphics2D绘画验证码
        Graphics2D g = bi.createGraphics();
        g.setFont(font);
        Color color = new Color(66, 2, 82);
        g.setColor(color);
        g.setBackground(new Color(226, 226, 240));
        g.clearRect(0, 0, width, height);
        FontRenderContext context = g.getFontRenderContext();
        Rectangle2D bounds = font.getStringBounds(code.toString(), context);
        double x = (width - bounds.getWidth()) / 2;
        double y = (height - bounds.getHeight()) / 2;
        double ascent = bounds.getY();
        double baseY = y - ascent;
        g.drawString(code.toString(), (int) x, (int) baseY);
        g.dispose();
        try {
            ImageIO.write(bi, "jpg", output);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(code);
        return code.toString();
    }

    /**
     * 随机产生一个字符
     *
     * @return
     */
    public static char randomChar() {
        Random r = new Random();
        return s.charAt(r.nextInt(s.length()));
    }
}
