package test.pachong;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;

public class Test {

    public static void main(String[] args) {
        StringBuilder allText = new StringBuilder();
        for (int i = 1; i <= 50; i++) {
            System.out.println("正在爬取第" + i + "页内容。。。");
            // 建立连接，获取网页内容
            String html = ConnectionUtil.Connect("https://www.pengfu.com/xiaohua_" + i + ".html");
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
            allText.append("-------------第").append(i).append("页-------------").append("\r\n");
            System.out.println("第" + i + "页内容爬取完毕。。。");
        }

        //将内容写入磁盘
        Test.writeToFile(allText.toString());
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
