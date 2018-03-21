package novel.spider.interfaces;

import novel.spider.entitys.Chapter;

import java.util.List;

/**
 * Created by Yang on 2018/3/11 0011.
 */
public interface IChapterSpider {
    /**
     *  给我们一个完整的url，我们就给你返回所有的章节列表
     */
    public List<Chapter> getChapter(String url);
}
