package test.spider;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Test {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        long startTime = System.currentTimeMillis();
        // 创建大小为5的线程池
        ExecutorService esPool = Executors.newFixedThreadPool(5);
        List<Future<StringBuilder>> futureList = new ArrayList<>();
        Spider spider = new Spider();
        for (int i = 1; i <= 10; i++) {
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
        if (finishCount.size() == 10) {
            StringBuilder allText = new StringBuilder();
            /*
             * finishCount中future.get()的顺序 和 futureList中的future顺序一致
             * 所以内容是从第1页...第N页顺序写入
             */
            for (StringBuilder pageNum : finishCount) {
                allText.append(pageNum);
            }
            // 写入磁盘
            Test.writeToFile(allText.toString());
            long endTime = System.currentTimeMillis();
            System.out.println("耗时 : " + (endTime - startTime));
            // 关闭线程池
            esPool.shutdownNow();
        }
    }

    private static void getContent(String articleLink) {
        System.out.println(articleLink);
        String contentHtml = ConnectionUtil.Connect("https://segmentfault.com/a/1190000015935519");
        Document contentDoc = Jsoup.parse(contentHtml);
        String title = contentDoc.select("#articleTitle").text();
        String author = contentDoc.select("span.name").text();
        String content = contentDoc.select("div.show-content").html();
        Test.writeToFile2(title, author, content);
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

    private static void writeToFile2(String title, String author, String content) {
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
            bos.write(title.getBytes());
            bos.write("\r\n".getBytes());
            author = "作者 : " + author;
            bos.write(author.getBytes());
            bos.write("\r\n".getBytes());
            bos.write(content.getBytes());
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
    }
}
