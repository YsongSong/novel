package novel.spider.util;

import novel.spider.enums.NovelSiteEnum;
import novel.spider.impl.BxwxChapterSpider;
import novel.spider.impl.DefaultChapterSpider;
import novel.spider.interfaces.IChapterSpider;

/**
 * Created by Yang on 2018/3/15 0015.
 */
public final class ChapterSpiderFactory {
    private ChapterSpiderFactory() {}

    /**
     * 通过给定的url，返回一个实现了IChapterSpider接口的实现类
     * @param url
     * @return
     */
    public static IChapterSpider getChapterSpider(String url) {
        NovelSiteEnum novelSiteEnum = NovelSiteEnum.getEnumByUrl(url);
        IChapterSpider chapterSpider = null;
        switch (novelSiteEnum) {
            case BiXiaWenXue:chapterSpider = new BxwxChapterSpider();break;
            case BiQuGe:
            case KanShuZhong:
            case DingDianXiaoShuo:
                chapterSpider = new DefaultChapterSpider();
                break;
        }
        return chapterSpider;
    }
}
