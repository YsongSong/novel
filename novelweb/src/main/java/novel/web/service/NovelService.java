package novel.web.service;

import novel.spider.entitys.Novel;
import novel.web.mapper.NovelMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Yang on 2018/4/14 0014.
 */
public interface NovelService {
    /**
     * 通过关键词，去数据库中查询结果，然后返回想要的结果
     * @param keyword
     * @return
     */
    public List<Novel> getNovelByKeyword(String keyword);

    /**
     * 查找对应平台下面的小说
     * @param keyword
     * @param platformId
     * @return
     */
    public List<Novel> getNovelByKeyword(String keyword, int platformId);
}
