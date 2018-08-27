package test.common;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class GraphicsTest {
    public static void test() throws IOException {
        // BufferedImage image = new BufferedImage(78, 20, BufferedImage.TYPE_INT_RGB);
        File filePath = new File("/users/shibo/tmp/header.jpg");
        BufferedImage image = ImageIO.read(new FileInputStream(filePath));
        /*Graphics2D g = image.createGraphics();
        //设置笔刷白色
        g.setColor(Color.WHITE);
        //填充整个屏幕
        g.fillRect(0,0,width,height);
        //设置笔刷蓝色
        g.setColor(Color.BLACK);

        g.setFont(new Font("宋体", Font.PLAIN, 16));
        //绘制文字
        g.drawString("ABCDEF", 2, image.getHeight() - 2);*/
        int[] p = image.getRGB(0, 0, image.getWidth(), image.getHeight(),
                new int[image.getWidth() * image.getHeight()], 0, image.getWidth());

        /*File outputFile = new File("/users/shibo/abc.jpg");
        if(outputFile.exists()){
            outputFile.delete();
        }
        ImageIO.write(image, "png",outputFile);*/
        int width = image.getWidth();
        int height = image.getHeight();
        int minx = image.getMinX();
        int miny = image.getMinY();
        String fillSymbol = " ";

        for (int i = minx; i < height; i++) {
            for (int j = miny+1; j < width; j++) {
                int pixel = image.getRGB(i, j);
                int prePixel = image.getRGB(i, j-1);
                if(pixel == prePixel){
                    fillSymbol = fillSymbol.equals(" ") ? "*" : " ";
                }
                String printSymbol = fillSymbol  + (j == image.getWidth() - 1 ? "\r\n" : "");
                System.out.print(printSymbol);
                // 下面三行代码将一个数字转换为RGB数字
                /*rgb[0] = (pixel & 0xff0000) >> 16;
                rgb[1] = (pixel & 0xff00) >> 8;
                rgb[2] = (pixel & 0xff);
                System.out.println("i=" + i + ",j=" + j + ":(" + rgb[0] + ","
                        + rgb[1] + "," + rgb[2] + ")");*/
            }
        }
        System.out.println("width=" + width + ",height=" + height + ".");
        System.out.println("minx=" + minx + ",miniy=" + miny + ".");


/*        for (int i = 0; i < image.getHeight(); i++){
            for (int j = 0; j < image.getWidth(); j++){
                String printSymbol = p[i * image.getWidth() + j] == -1 ? (i % 2 == 0 ? "*" : "*") : " " + (j == image.getWidth() - 1 ? "\n" : "");
            }
        }*/
    }

    public static void main(String args[]) throws IOException {
        test();
    }
}
