package novel.web.controller;

import novel.spider.entitys.Chapter;
import novel.spider.entitys.ChapterDetail;
import novel.spider.entitys.Novel;
import novel.spider.impl.DefaultChapterDetailSpider;
import novel.spider.interfaces.IChapterDetailSpider;
import novel.spider.interfaces.IChapterSpider;
import novel.spider.util.ChapterSpiderFactory;
import novel.spider.util.NovelSpiderUtil;
import novel.web.entitys.JSONResponse;
import novel.web.service.NovelService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Yang on 2018/4/14 0014.
 */

@Controller
public class NovelController {
    @Resource
    private NovelService service;

    @RequestMapping(value = "getChapter.do", method = RequestMethod.GET)
    @ResponseBody
    public JSONResponse getNovelChapter(String url) {
        IChapterSpider chapterSpider = ChapterSpiderFactory.getChapterSpider(url);
        List<Chapter> chapters = chapterSpider.getChapter(url);
        return JSONResponse.success(chapters);
    }

    @RequestMapping(value = "getChapterDetail.do", method = RequestMethod.GET)
    @ResponseBody
    public JSONResponse getChapterDetail(String url) {
        IChapterDetailSpider chapterDetailSpider = new DefaultChapterDetailSpider();
        ChapterDetail detail = chapterDetailSpider.getChapterDetail(url);
        return JSONResponse.success(detail);
    }

    @RequestMapping(value = "serch.do", method = RequestMethod.GET)
    @ResponseBody
    public JSONResponse getNovelByKeyword(String keyword) {
        List<Novel> novels = service.getNovelByKeyword(keyword);
        return JSONResponse.success(novels);
    }
}
