package test.common;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class GraphicsTest3 {
    // private static char[] cs = new char[]{'.', ',', '*', '+', '=', '&', '$', '@', '#', ' '};
    private static char[] cs = new char[]{'.', ' '};

    public static void test() throws IOException {

        // BufferedImage image = new BufferedImage(78, 20, BufferedImage.TYPE_INT_RGB);
        File filePath = new File("D:/header2.png");
        BufferedImage image = ImageIO.read(new FileInputStream(filePath));
        char[][] css = new char[image.getWidth()][image.getHeight()];
        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                int rgb = image.getRGB(x, y);
                Color c = new Color(rgb);
                // 得到灰度值
                int cc = (c.getRed() + c.getGreen() + c.getBlue()) / 3;
                css[x][y] = cs[(int) ((cc * 10 - 1) / 255)];
            }
        }
        StringBuffer sb = new StringBuffer();
        // 开始拼接内容
        for (int y = 0; y < css[0].length; y++) {
            for (int x = 0; x < css.length; x++) {
                sb.append(css[x][y]);
            }
            sb.append("\r\n");
        }
        System.out.println(sb.toString());
    }

    public static void main(String args[]) throws IOException {
        test();
    }
}
