package test.mytest;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Test {

    public static void main(String[] args) {
        String html = ConnectionUtil.Connect("https://segmentfault.com/");
        Document doc = Jsoup.parse(html);
        Elements elements = doc.getElementsByClass("news__item-title");

        for(Element element : elements){
            System.out.println(element.html());
        }
    }
}
