package test.spider;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import test.spider.ConnectionUtil;

import java.io.*;

public class CloudMusicSpider {

    public static void main(String[] args) {
        StringBuilder allText = new StringBuilder();
        // 建立连接，获取网页内容
        String html = ConnectionUtil.Connect("https://music.163.com/");
        // 将内容转换成dom格式，方便操作
        Document doc = Jsoup.parse(html);
        // 获取网页内所有标题节点
        Elements titles = doc.select("h1.dp-b");
        for (Element titleEle : titles) {
            Element parent = titleEle.parent();
            // 标题内容
            String title = titleEle.getElementsByTag("a").text();
            // 标题对应的作者
            String author = parent.select("p.user_name_list > a").text();
            // 标题对应的正文
            String content = parent.select("div.content-img").text();
            // 将内容格式化
            allText.append(title)
                    .append("\r\n作者：").append(author)
                    .append("\r\n").append(content)
                    .append("\r\n").append("\r\n");
        }

        //将内容写入磁盘
        CloudMusicSpider.writeToFile(allText.toString());
    }


    /**
     * 将内容写入到磁盘
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
}