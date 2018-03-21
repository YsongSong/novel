package novel.spider.util;

import novel.spider.enums.NovelSiteEnum;
import novel.spider.impl.DefaultChapterDetailSpider;
import novel.spider.interfaces.IChapterDetailSpider;

/**
 * 通过给定的url，返回一个实现了IChapterDetailSpider接口的实现类
 * Created by Yang on 2018/3/15 0015.
 */
public final class ChapterDetailSpiderFactory {
    private ChapterDetailSpiderFactory() {}

    public static IChapterDetailSpider getChapterDetailSpider(String url) {
        NovelSiteEnum novelSiteEnum = NovelSiteEnum.getEnumByUrl(url);
        IChapterDetailSpider chapterDetailSpider = null;
        switch (novelSiteEnum) {
            case BiQuGe:
            case BiXiaWenXue:
            case DingDianXiaoShuo:
            case KanShuZhong:
                chapterDetailSpider = new DefaultChapterDetailSpider();
                break;
        }
        return chapterDetailSpider;
    }

}
