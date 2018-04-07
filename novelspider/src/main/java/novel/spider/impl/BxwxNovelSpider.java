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
 * 笔下文学网站的书籍列表爬取
 * Created by Yang on 2018/4/7 0007.
 */
public class BxwxNovelSpider extends AbstractNovelSpider {

    public BxwxNovelSpider() {}

    @Override
    public List<Novel> getNovel(String url) {
        List<Novel> novels = new ArrayList<Novel>();
        try {
            Elements trs = super.getsTr(url, 2);
            for(int index = 1, size = trs.size(); index < size; index ++) {
                Element tr = trs.get(index);
                Elements tds = tr.getElementsByTag("td");
                Novel novel = new Novel();
                novel.setName(tds.get(0).text());
                String novelUrl = tds.get(0).getElementsByTag("a").first().absUrl("href").replace(".htm", "/").replace("/binfo/", "/b/");
                novel.setUrl(novelUrl);
                novel.setLastUpdateChapter(tds.get(1).text());
                novel.setLastUpdateChapterUrl(tds.get(1).getElementsByTag("a").first().absUrl("href"));
                novel.setAuthor(tds.get(2).text());
                novel.setLastUpdateTime(NovelSpiderUtil.getDate("tds.get(4).text())", "yyyy-MM-dd"));
                novel.setStatus(NovelSpiderUtil.getNovelStatus(tds.get(5).text()));
                novel.setPlatformId(NovelSiteEnum.getEnumByUrl(url).getId());
                novels.add(novel);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
