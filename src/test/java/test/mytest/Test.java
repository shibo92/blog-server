package test.mytest;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;

public class Test {

    public static void main(String[] args) {
        /*String html = ConnectionUtil.Connect("https://segmentfault.com/hottest");
        Document doc = Jsoup.parse(html);
        Elements titles = doc.select("h4.news__item-title");
        for(Element title : titles){
            String articleLink = title.parent().parent().attr("href");
            Test.getContent(articleLink);
        }*/
        Test.getContent("/a/1190000015645674");
    }

    private static void getContent(String articleLink){
        String contentHtml = ConnectionUtil.Connect("https://segmentfault.com" + articleLink);
        // String contentHtml = ConnectionUtil.Connect("https://segmentfault.com/a/1190000015935519");
        Document contentDoc = Jsoup.parse(contentHtml);
        String title = contentDoc.select("#articleTitle").text();
        String author = contentDoc.select("div.article__author strong").html();
        String content = contentDoc.select("div.article__content").html();

        Test.writeToFile(title,author,content);
    }

    private static void writeToFile(String title,String author, String content){
        BufferedOutputStream bos = null;
        try {
            File targetFile = new File("D://segmentfault_articles/"+title+".html");
            File fileDir = targetFile.getParentFile();
            if(!fileDir.exists()){
                fileDir.mkdirs();
            }
            if(!targetFile.exists()){
                targetFile.createNewFile();
            }
            bos = new BufferedOutputStream(new FileOutputStream(targetFile,true));
            bos.write(title.getBytes());
            bos.write("\r\n".getBytes());
            author = "作者 : " + author;
            bos.write(author.getBytes());
            bos.write("\r\n".getBytes());
            bos.write(content.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            if(null != bos){
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
