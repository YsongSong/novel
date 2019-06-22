package novel.spider.impl.abstracts;

import novel.spider.entitys.ChapterDetail;
import novel.spider.enums.NovelSiteEnum;
import novel.spider.impl.abstracts.AbstractSpider;
import novel.spider.interfaces.IChapterDetailSpider;
import novel.spider.util.ChapterDetailUtil;
import novel.spider.util.NovelSpiderUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.Map;

/**
 * Created by Yang on 2018/3/13 0013.
 */
public abstract class AbstractChapterDetailSpider extends AbstractSpider implements IChapterDetailSpider {

    @Override
    public ChapterDetail getChapterDetail(String url) {
        try {
            String result = super.crawl(url);
            result = result.replace("&nbsp;", " ").replace("<br />", "${line}").replace("<br/>", "${line}");
            Element doc =  Jsoup.parse(result);
            doc.absUrl(url);
            Map<String, String> context = NovelSpiderUtil.getContext(NovelSiteEnum.getEnumByUrl(url));
            ChapterDetail detail = new ChapterDetail();
            String baseUrl = context.get("url");

            //  拿标题内容
            String titleSelector = context.get("chapter-detail-title-selector");
            String[] splits = titleSelector.split("\\,");
            splits = ChapterDetailUtil.pareseSelector(splits);
            detail.setTitle(doc.select(splits[0]).get(Integer.parseInt(splits[1])).text());

            //  拿章节内容
            String contentSelector = context.get("chapter-detail-content-selector");
            detail.setContent(doc.select(contentSelector).first().text().replace("${line}", "\n"));

            //拿前一章的地址
            String prevSelector = context.get("chapter-detail-prev-selector");
            splits = prevSelector.split("\\,");
            splits = ChapterDetailUtil.pareseSelector(splits);
            Elements e = doc.select(prevSelector);
//            detail.setPrev(doc.select(splits[0]).get(Integer.parseInt(splits[1])).absUrl("href"));
            String prev = e.get(Integer.parseInt(splits[1])).attr("href");
            detail.setPrev(baseUrl + prev);

            //拿后一章的地址
            String nextSelector = context.get("chapter-detail-next-selector");
            splits = nextSelector.split("\\,");
            splits = ChapterDetailUtil.pareseSelector(splits);
//            detail.setNext(doc.select(splits[0]).get(Integer.parseInt(splits[1])).absUrl("href"));
            String next = e.get(Integer.parseInt(splits[1])).attr("href");
            detail.setNext(baseUrl + next);

            return detail;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public ChapterDetail getChapterDetail(String url, String baseUrl) {
        try {
            String result = super.crawl(url);
            result = result.replace("&nbsp;", " ").replace("<br />", "${line}").replace("<br/>", "${line}");
            Element doc =  Jsoup.parse(result);
            doc.absUrl(url);
            Map<String, String> context = NovelSpiderUtil.getContext(NovelSiteEnum.getEnumByUrl(url));
            ChapterDetail detail = new ChapterDetail();
            if (baseUrl==null) {
                baseUrl = context.get("url");
            }

            //  拿标题内容
            String titleSelector = context.get("chapter-detail-title-selector");
            String[] splits = titleSelector.split("\\,");
            splits = ChapterDetailUtil.pareseSelector(splits);
            detail.setTitle(doc.select(splits[0]).get(Integer.parseInt(splits[1])).text());

            //  拿章节内容
            String contentSelector = context.get("chapter-detail-content-selector");
            detail.setContent(doc.select(contentSelector).first().text().replace("${line}", "\n"));

            //拿前一章的地址
            String prevSelector = context.get("chapter-detail-prev-selector");
            splits = prevSelector.split("\\,");
            splits = ChapterDetailUtil.pareseSelector(splits);
            Elements e = doc.select(prevSelector);
//            detail.setPrev(doc.select(splits[0]).get(Integer.parseInt(splits[1])).absUrl("href"));
            String prev = e.get(Integer.parseInt(splits[1])).attr("href");
            detail.setPrev(baseUrl + prev);

            //拿后一章的地址
            String nextSelector = context.get("chapter-detail-next-selector");
            splits = nextSelector.split("\\,");
            splits = ChapterDetailUtil.pareseSelector(splits);
//            detail.setNext(doc.select(splits[0]).get(Integer.parseInt(splits[1])).absUrl("href"));
            String next = e.get(Integer.parseInt(splits[1])).attr("href");
            detail.setNext(baseUrl + next);


            return detail;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
