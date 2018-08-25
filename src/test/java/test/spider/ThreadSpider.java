package test.spider;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadSpider implements Callable {
    // 使用atomicInteger保证共享变量的安全自增
    // private AtomicInteger pageNum = new AtomicInteger(0);
    volatile private Integer pageNum = 0;
    @Override
    public StringBuilder call() throws Exception {
        // 当前页码
        // Integer privateNum = this.pageNum.addAndGet(1);
        Integer privateNum = pageNum++;
        // 存储当前页的文本
        StringBuilder currentPageText = new StringBuilder();

        System.out.println("正在爬取第" + privateNum + "页内容。。。");
        String html = ConnectionUtil.Connect("https://www.pengfu.com/xiaohua_" + privateNum + ".html");
        Document doc = Jsoup.parse(html);
        Elements titles = doc.select("h1.dp-b");
        for (Element titleEle : titles) {
            Element parent = titleEle.parent();
            String title = titleEle.getElementsByTag("a").text();
            String author = parent.select("p.user_name_list > a").text();
            String content = parent.select("div.content-img").text();
            // 将内容格式化
            currentPageText.append(title)
                    .append("\r\n作者：").append(author)
                    .append("\r\n").append(content)
                    .append("\r\n").append("\r\n");
        }
        currentPageText.append("-------------第").append(privateNum).append("页-------------").append("\r\n");
        System.out.println("第" + privateNum + "页内容爬取完毕。。。");
        // 将当前页内容返回给future对象
        return currentPageText;
    }
}
