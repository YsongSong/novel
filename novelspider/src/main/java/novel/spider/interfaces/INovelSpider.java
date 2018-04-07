package novel.spider.interfaces;

import novel.spider.entitys.Novel;

import java.util.List;

/**
 * 爬取某个站点的小说列表
 * Created by Yang on 2018/4/5 0005.
 */
public interface INovelSpider {
    /** 抓取某一个页面时最大的尝试次数3 */
    public static final int MAX_TRY_TIMES = 3;

    /** 给我一个URL，就返回一堆的小说实体 */
    public List<Novel> getNovel(String url);
}
