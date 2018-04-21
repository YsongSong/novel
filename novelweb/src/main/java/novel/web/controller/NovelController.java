package novel.web.controller;

import novel.spider.entitys.Chapter;
import novel.spider.entitys.ChapterDetail;
import novel.spider.entitys.Novel;
import novel.spider.interfaces.IChapterDetailSpider;
import novel.spider.interfaces.IChapterSpider;
import novel.spider.util.ChapterDetailSpiderFactory;
import novel.spider.util.ChapterSpiderFactory;
import novel.spider.util.NovelSpiderUtil;
import novel.web.entitys.JSONResponse;
import novel.web.service.NovelService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Created by Yang on 2018/4/14 0014.
 */

@Controller
public class NovelController {
    @Resource
    private NovelService service;

    @RequestMapping(value = "/getChapter.do", method = RequestMethod.GET)
    @ResponseBody
    public JSONResponse getNovelChapter(String url) {
        IChapterSpider chapterSpider = ChapterSpiderFactory.getChapterSpider(url);
        List<Chapter> chapters = chapterSpider.getChapter(url);
        return JSONResponse.success(chapters);
    }

    @RequestMapping(value = "/getChapterDetail.do", method = RequestMethod.GET)
    @ResponseBody
    public JSONResponse getChapterDetail(String url) {
        IChapterDetailSpider chapterDetailSpider = ChapterDetailSpiderFactory.getChapterDetailSpider(url);
        ChapterDetail detail = chapterDetailSpider.getChapterDetail(url);
        return JSONResponse.success(detail);
    }

    @RequestMapping(value = "/search.do", method = RequestMethod.POST)
    @ResponseBody
    public JSONResponse getNovelByKeyword(String keyword) throws UnsupportedEncodingException {
//        keyword = new String(keyword.getBytes("ISO-8859-1"), "UTF-8");
        List<Novel> novels = service.getNovelByKeyword(keyword);
        return JSONResponse.success(novels);
    }

    @RequestMapping(value = "/search2.do", method = RequestMethod.POST)
    @ResponseBody
    public JSONResponse getNovelByKeyword(String keyword, int platformId) throws UnsupportedEncodingException {
//        keyword = new String(keyword.getBytes("ISO-8859-1"), "UTF-8");
        return JSONResponse.success(service.getNovelByKeyword(keyword, platformId));
    }

    @RequestMapping(value = "/chapterList.do", method = RequestMethod.GET)
    public ModelAndView showChapterList(String url) {
        ModelAndView view = new ModelAndView();
        view.setViewName("chapterList");
        view.getModel().put("chapters",ChapterSpiderFactory.getChapterSpider(url).getChapter(url));
        view.getModel().put("baseUrl", url);
        return view;
    }

    @RequestMapping(value = "chapterDetail.do", method = RequestMethod.GET)
    public ModelAndView showChapterDetail(String url, String baseUrl) {
        ModelAndView view = new ModelAndView();
        view.setViewName("chapterDetail");
        try {
            IChapterDetailSpider detailSpider = ChapterDetailSpiderFactory.getChapterDetailSpider(url);
            ChapterDetail detail = detailSpider.getChapterDetail(url, baseUrl);
            detail.setContent(detail.getContent().replaceAll("\n", "<br>"));
            view.getModel().put("chapterDetail", detail);
            view.getModel().put("isSuccess", true);
        } catch (Exception e) {
            e.printStackTrace();
            view.getModel().put("isSuccess", false);
        }
        view.getModel().put("baseUrl", baseUrl);
        return view;
    }
}
