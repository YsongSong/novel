package novel.spider.interfaces;

import novel.spider.entitys.ChapterDetail;

/**
 * Created by Yang on 2018/3/13 0013.
 */
public interface IChapterDetailSpider {

    /**
     * 给我一个url，我就给你一个对应网站的章节内容实体
     */
    public ChapterDetail getChapterDetail(String url) ;
}
