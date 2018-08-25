package test.spider;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Test {

    public static void main(String[] args) throws ExecutionException, InterruptedException, IOException {
        long startTime = System.currentTimeMillis();
        // 创建大小为5的线程池
        ExecutorService esPool = Executors.newFixedThreadPool(5);
        List<Future<StringBuilder>> futureList = new ArrayList<>();
        ThreadSpider spider = new ThreadSpider();
        for (int i = 1; i <= 1; i++) {
            futureList.add(esPool.submit(spider));
        }

        List<StringBuilder> finishCount = new ArrayList<>();
        for (Future<StringBuilder> future : futureList) {
            // 线程结束，将线程返回的内容添加到list
            finishCount.add(future.get());
        }

        /*
         * 所有内容爬取完毕，将内容统一写入磁盘
         */
        if (finishCount.size() == 1) {
            StringBuilder allText = new StringBuilder();
            /*
             * finishCount中future.get()的顺序 和 futureList中的future顺序一致
             * 所以内容是从第1页...第N页顺序写入
             */

            for (StringBuilder pageNum : finishCount) {
                allText.append(pageNum);
            }
            String[] tmpArr = allText.toString().split("\r\n");
            int maxHeight = (tmpArr.length - 1) * 5;
            int maxLine = tmpArr[2].length() * 5;
            // 写入磁盘
            // Test.writeToFile(allText.toString());
            long endTime = System.currentTimeMillis();

            System.out.println("耗时 : " + (endTime - startTime));
            // 关闭线程池
            esPool.shutdownNow();
        }
    }

    /**
     * 将内容写入到磁盘
     *
     * @param allText
     */
    private static void writeToFile(String allText) {
        System.out.println("正在写入。。。");
        BufferedOutputStream bos = null;
        try {
            File targetFile = new File("/Users/shibo/tmp/pengfu.txt");
            File fileDir = targetFile.getParentFile();
            if (!fileDir.exists()) {
                fileDir.mkdirs();
            }
            if (!targetFile.exists()) {
                targetFile.createNewFile();
            }
            bos = new BufferedOutputStream(new FileOutputStream(targetFile, true));
            bos.write(allText.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != bos) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("写入完毕。。。");
    }

    private static void writeToPic(String content, int maxLine, int maxHeight) throws IOException {
        int width = 78;
        int height = 20;
        BufferedImage image = new BufferedImage(maxLine, maxHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = image.createGraphics();
        //设置笔刷白色
        g.setColor(Color.WHITE);
        //填充整个屏幕
        g.fillRect(0,0,maxLine,maxHeight);
        //设置笔刷蓝色
        g.setColor(Color.BLACK);
        g.setFont(new Font("宋体", Font.PLAIN, 16));
        //绘制文字
        g.drawString(content, 2, image.getHeight() - 2);
        int[] p = image.getRGB(0, 0, image.getWidth(), image.getHeight(),
                new int[image.getWidth() * image.getHeight()], 0, image.getWidth());

        File outputFile = new File("/users/shibo/tmp/abc.jpg");
        if(outputFile.exists()){
            outputFile.delete();
        }
        ImageIO.write(image, "png",outputFile);
        System.out.println("绘制完毕。。。");
    }
}
