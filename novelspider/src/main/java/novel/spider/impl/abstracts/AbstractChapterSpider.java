package novel.spider.impl.abstracts;

import novel.spider.entitys.Chapter;
import novel.spider.enums.NovelSiteEnum;
import novel.spider.impl.abstracts.AbstractSpider;
import novel.spider.interfaces.IChapterSpider;
import novel.spider.util.NovelSpiderUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yang on 2018/3/11 0011.
 */
public abstract class AbstractChapterSpider extends AbstractSpider implements IChapterSpider {

    public List<Chapter> getChapter(String url) {
        try {
            String result = crawl(url);
            Document doc = Jsoup.parse(result);
            doc.setBaseUri(url);
            Elements as = doc.select(NovelSpiderUtil.getContext(NovelSiteEnum.getEnumByUrl(url)).get("chapter-list-selector"));
            List<Chapter> chapteres = new ArrayList<Chapter>();
            for(Element a : as) {
                Chapter chapter = new Chapter();
                chapter.setTitle(a.text());
                chapter.setUrl(a.absUrl("href"));
                chapteres.add(chapter);
            }
            return chapteres;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
