package novel.spider.impl;

import novel.spider.entitys.Novel;
import novel.spider.enums.NovelSiteEnum;
import novel.spider.impl.abstracts.AbstractNovelSpider;
import novel.spider.util.NovelSpiderUtil;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * 看书中网站的书籍列表爬取
 * Created by Yang on 2018/4/9 0009.
 */
public class KanShuZhongNovelSpider extends AbstractNovelSpider {
    public KanShuZhongNovelSpider() {}

    @Override
    public List<Novel> getsNovel(String url, Integer maxTryTimes) {
        List<Novel> novels = new ArrayList<Novel>();
        try {
            Elements trs = super.getsTr(url,maxTryTimes);
            for (int index = 1, size = trs.size(); index < size - 1; index ++) {
                Element tr = trs.get(index);
                Elements tds = tr.getElementsByTag("td");
                Novel novel = new Novel();
                novel.setType(tds.first().text());
                novel.setName(tds.get(1).text());
                novel.setUrl(tds.get(1).getElementsByTag("a").first().absUrl("href"));
                novel.setLastUpdateChapter(tds.get(2).text());
                novel.setLastUpdateChapterUrl(tds.get(2).getElementsByTag("a").first().absUrl("href"));
                novel.setAuthor(tds.get(3).text());
                novel.setLastUpdateTime(NovelSpiderUtil.getDate(tds.get(4).text(), "MM-dd"));
                novel.setStatus(NovelSpiderUtil.getNovelStatus(tds.get(5).text()));
                novel.setPlatformId(NovelSiteEnum.getEnumByUrl(url).getId());
                novels.add(novel);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return novels;
    }
}
