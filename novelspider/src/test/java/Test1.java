import novel.spider.configuration.Configuration;
import novel.spider.entitys.Chapter;
import novel.spider.entitys.ChapterDetail;
import novel.spider.entitys.Novel;
import novel.spider.enums.NovelSiteEnum;
import novel.spider.impl.*;
import novel.spider.impl.abstracts.AbstractChapterDetailSpider;
import novel.spider.impl.abstracts.AbstractChapterSpider;
import novel.spider.interfaces.IChapterDetailSpider;
import novel.spider.interfaces.IChapterSpider;
import novel.spider.interfaces.INovelDownload;
import novel.spider.interfaces.INovelSpider;
import novel.spider.util.NovelSpiderUtil;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Yang on 2018/3/9 0009.
 */
public class Test1 {
    @Test
    public void print(){
        System.out.println("123");
        IChapterSpider chapterSpider = new DefaultChapterSpider();
        List<Chapter> chapters = chapterSpider.getChapter("http://www.23wx.cc/du/59/59652/");
        for (Chapter chapter : chapters) {
            System.out.println(chapter);
        }
    }

    @Test
    public void testUtil() {
        Map<String, String> map = NovelSpiderUtil.getContext(NovelSiteEnum.getEnumByUrl("http://www.23wx.cc"));
        System.out.println(map);
    }

    @Test
    public void testC() {
        int i = 5, j = 5, p, q;
//        int x = 5;
//        System.out.println(x++);
        p = (i++) + (i++) + (i++);
        q = (++j) + (++j) + (++j);
        System.out.println(i);
        System.out.println(j);
        System.out.println(p);
        System.out.println(q);

    }

    @Test
    public void test1() {
        File file = new File("youaresb.xml");
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void testChapterDetail() {
        IChapterDetailSpider chapterDeailSpider = new DefaultChapterDetailSpider();
        ChapterDetail detail = chapterDeailSpider.getChapterDetail("https://www.bxwx9.org/b/5/5740/41209550.html");
        System.out.println(detail);
    }

    @Test
    public void testGetsChapter() throws Exception {
        IChapterSpider spider = new DefaultChapterSpider();
        List<Chapter>  chapters  = spider.getChapter("https://www.xs.la/0_5/");
        for (Chapter chapter : chapters) {
            System.out.println(chapter);
        }
    }
    /**
     * 该测试方法用来获取看书中网站的章节列表
     * @throws Exception
     */
    @Test
    public void testGetsChapter2() throws Exception {
        IChapterSpider spider = new DefaultChapterSpider();
        List<Chapter>  chapters  = spider.getChapter("http://www.kanshuzhong.com/book/103251/");
        for (Chapter chapter : chapters) {
            System.out.println(chapter);
        }
    }
    /**
     * 测试是否能够正确的拿到笔下文学的章节列表
     * @throws Exception
     */
    @Test
    public void testGetsChapter3() throws Exception {
        IChapterSpider spider = new BxwxChapterSpider();
        List<Chapter>  chapters  = spider.getChapter("https://www.bxwx9.org/b/5/5740/");
        for (Chapter chapter : chapters) {
            System.out.println(chapter);
        }
    }

    /**
     * 测试获取网站解析内容
     */
    @Test
    public void testGetSiteContext() {
        System.out.println(NovelSpiderUtil.getContext(NovelSiteEnum.getEnumByUrl("http://www.23wx.cc/du/59/59652/")));
    }

    /**
     * 测试顶点小说章节详细内容的获取
     */
    @Test
    public void testGetChapterDetail() {
        IChapterDetailSpider spider = new DefaultChapterDetailSpider();
        System.out.println(spider.getChapterDetail("http://www.23wx.cc/du/59/59652/6974953.html").getContent());
    }

    /**
     * 该测试方法用于测试是否能拿到看书中网站的章节详细内容
     */
    @Test
    public void testGetChapterDetail2() {
        IChapterDetailSpider spider = new DefaultChapterDetailSpider();
        System.out.println(spider.getChapterDetail("http://www.kanshuzhong.com/book/103251/18491186.html"));
    }
    /**
     * 该测试方法用于测试手否能正确拿到笔下文学的章节消息内容
     */
    @Test
    public void testGetChapterDetail3() {
        IChapterDetailSpider spider = new DefaultChapterDetailSpider();
        System.out.println(spider.getChapterDetail("https://www.bxwx9.org/b/165/165072/27304945.html"));
    }

    @Test
    public void testO() {
        int a = 5;
//        System.out.println(a++);
        if(a++==5){
            System.out.println(a);
        } else {
            System.out.println(a--);
        }
    }

    @Test
    public void testDownload() {
        INovelDownload download = new NovelDownload();
        Configuration config = new Configuration();
        config.setPath("D:/1");
        config.setSize(50);
        System.out.println("下载好了，文件保存在：" + download.download("https://www.bxwx9.org/b/5/5740/", config) + "这里，赶紧去看看吧！");
    }

    @Test
    public void testDownload1() {
        INovelDownload download = new NovelDownload();
        Configuration config = new Configuration();
        download.download("https://www.bxwx9.org/b/5/5740/",config);
    }

    @Test
    public void test2() {
        String str = "abcdef1234";
        String []str1 = new String [10];
        for(int i = 10;i>0;i--) {
            System.out.print(str.toCharArray()[i-1]);
        }
//        for (int i = 0;i<str.length(); i++) {
//            str1[i] = str.substring(str.length() - i - 1,str.length() - i );
//            System.out.print(str1[i]);
//        }
        System.out.println(str.charAt(4));
    }

    @Test
    public void testMerge() {
        NovelSpiderUtil.mulitFileMerge("D:/1",null,true);
    }

    @Test
    public void testTd() {
        INovelSpider novelSpider = new BxwxNovelSpider();
        List<Novel> novels =  novelSpider.getsNovel("https://www.bxwx9.org/modules/article/index.php?fullflag=1",3);
        for (Novel novel : novels) {
            System.out.println(novel);
        }
    }

    @Test
    public void testPrint() {
        INovelSpider novelSpider = new KanShuZhongNovelSpider();
        novelSpider.getsNovel("http://www.kanshuzhong.com/toplist/allvisit/1/",3);
    }

    @Test
    public void testKanShuZhongIterator() {
        INovelSpider spider = new KanShuZhongNovelSpider();
        Iterator<List<Novel>> iterator = spider.iterator("http://www.kanshuzhong.com/map/A/1/", 10);
        while (iterator.hasNext()) {
            List<Novel> novels = iterator.next();
            System.err.println("URL：" + spider.next());
//			for (Novel novel : novels) {
//				System.out.println(novel);
//			}
        }
    }

    @Test
    public void testBxwxIterator() {
        INovelSpider spider = new BxwxNovelSpider();
        Iterator<List<Novel>> iterator = spider.iterator("https://www.bxwx9.org/binitialE/0/1.htm", 3);
        while (iterator.hasNext()) {
            List<Novel> novels = iterator.next();
            System.err.println("URL：" + spider.next());
//			for (Novel novel : novels) {
//				System.out.println(novel);
//			}
        }
    }
}
