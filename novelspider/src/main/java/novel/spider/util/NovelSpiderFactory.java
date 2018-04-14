package novel.spider.util;

import novel.spider.enums.NovelSiteEnum;
import novel.spider.impl.BxwxNovelSpider;
import novel.spider.impl.KanShuZhongNovelSpider;
import novel.spider.interfaces.INovelSpider;

/**
 * 生产书籍列表的实现类
 * Created by Yang on 2018/4/13 0013.
 */
public final class NovelSpiderFactory {
    private NovelSpiderFactory() {}
    public static INovelSpider getNovelSpider(String url) {
        NovelSiteEnum novelSiteEnum = NovelSiteEnum.getEnumByUrl(url);
        switch (novelSiteEnum) {
            case BiXiaWenXue : return new BxwxNovelSpider();
            case KanShuZhong : return new KanShuZhongNovelSpider();
            default : throw new RuntimeException(url + "暂时不被支持");
        }
    }
}
