package com.wmost.spider.sample;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;

/**
 * @author code4crafter@gmail.com <br>
 * Date: 13-4-21
 * Time: 下午8:08
 */
public class NjuBBSProcessor implements PageProcessor {
    public void process(Page page) {
        List<String> requests = page.getHtml().regex("<a[^<>]*href=(bbstcon\\?board=Pictures&file=[^>]*)").all();
        page.addTargetRequests(requests);
        page.putField("title",page.getHtml().xpath("//div[@id='content']//h2/a"));
        page.putField("content",page.getHtml().smartContent());
    }

    public Site getSite() {
        return Site.me().setDomain("bbs.nju.edu.cn");
    }

    public static void main(String[] args) {
        Spider.create(new NjuBBSProcessor()).addUrl("http://bbs.nju.edu.cn/board?board=Pictures").run();
    }
}
