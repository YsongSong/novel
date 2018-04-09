package novel.spider.interfaces;

import novel.spider.entitys.Novel;

import java.util.Iterator;
import java.util.List;

/**
 * 爬取某个站点的小说列表
 * Created by Yang on 2018/4/5 0005.
 */
public interface INovelSpider {
    /** 抓取某一个页面时最大的尝试次数3 */
    public static final int MAX_TRY_TIMES = 3;

    /** 给我一个URL，就返回一堆的小说实体 */
    public List<Novel> getsNovel(String url, Integer maxTryTimes);
    /** 判断有没有下一页 */
    public boolean hasNext();
    /** 下一页的URL */
    public String next();
    public Iterator<List<Novel>> iterator(String firstPage, Integer maxTryTimes);
    /**
     * List<Novel> novels = new ArrayList<>();
     * 假设novels中有很多的元素
     * for (int index = 0, size = novels.size(); index < size; index++) {
     * 	System.out.println("第" + index + "个元素是：" + novel);
     * }
     * for (Novel novel : novels) {
     * 	System.out.println(novel);
     * }
     * Iterator<Novel> iterator = novels.iterator();
     * while (iterator.hasNext()) {
     * 	Novel novel = iterator.next();
     * 	System.out.println(novel);
     * }
     * */
}
