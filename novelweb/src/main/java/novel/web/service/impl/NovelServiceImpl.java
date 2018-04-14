package novel.web.service.impl;

import novel.spider.entitys.Novel;
import novel.web.mapper.NovelMapper;
import novel.web.service.NovelService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Yang on 2018/4/14 0014.
 */
@Service
public class NovelServiceImpl implements NovelService {
    @Resource
    private NovelMapper mapper;
    @Override
    public List<Novel> getNovelByKeyword(String keyword) {
        keyword = "%" + keyword + "%";
        List<Novel> novels = mapper.getNovelByKeyword(keyword);
        return novels;
    }

    @Override
    public List<Novel> getNovelByKeyword(String keyword, int platformId) {

        return null;
    }
}
