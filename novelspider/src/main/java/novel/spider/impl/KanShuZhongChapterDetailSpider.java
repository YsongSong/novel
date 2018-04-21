package novel.spider.impl;

import novel.spider.entitys.ChapterDetail;
import novel.spider.impl.abstracts.AbstractChapterDetailSpider;

/**
 * Created by Yang on 2018/4/21 0021.
 */
public class KanShuZhongChapterDetailSpider extends AbstractChapterDetailSpider {
    @Override
    public ChapterDetail getChapterDetail(String url, String baseUrl) {
        System.out.println("看书中" + url );
        System.out.println(baseUrl);
        return super.getChapterDetail(url);
    }
}
