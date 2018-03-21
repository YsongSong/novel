package novel.spider.impl;

import novel.spider.configuration.Configuration;
import novel.spider.entitys.Chapter;
import novel.spider.entitys.ChapterDetail;
import novel.spider.interfaces.IChapterDetailSpider;
import novel.spider.interfaces.IChapterSpider;
import novel.spider.interfaces.INovelDownload;
import novel.spider.util.ChapterDetailSpiderFactory;
import novel.spider.util.ChapterSpiderFactory;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.concurrent.*;

/**
 * 如何实现多线程下载任意网站的小说
 *      1.拿到该网站的某本小说的所有章节：章节列表
 *      2.通过计算，将这些章节分配给指定数量的线程，让他们去解析，然后保存到文本文件中
 *      3.通知主线程，将这些零散的小文件合并成一个大文件。最后将那些分片的小文件删除掉。
 * Created by Yang on 2018/3/15 0015.
 */
public class NovelDownload implements INovelDownload {
    public String download(String url, Configuration config) {
        IChapterSpider chapterSpider = ChapterSpiderFactory.getChapterSpider(url);
        List<Chapter> chapters = chapterSpider.getChapter(url);
        // 某个线程下载完毕之后，得告诉主线程：下载完成了
        // 所有线程都下载完成之后，合并！
        int size = config.getSize();    // 拿到每个线程下载的章节数
        // 2010章  100个线程
        // 需要21个线程
        // int / int 的结果只能是int
        // double / double 结果依然是double
        // double / int 结果也是double
        // Math.ceil()是常见编程语言中的常用代码，用于向上取整数计算，返回的是大于或等于函数参数的数值
        int maxThreadSize = (int)Math.ceil(chapters.size() * 1.0 / size);
        Map<String, List<Chapter>> downloadTaskAlloc = new HashMap<String, List<Chapter>>();    // 下载任务分配
        for(int i = 0;i < maxThreadSize; i++) {
            // i = 0;下载0-99章 0-99.txt
            // i = 1;下载100-199章 100-199.txt
            // ...
            // i = 0 0-99	-- > 100  0 100
            // i = 1 100-199
            // i = 2 200-299
            // i = 3 300-399
            // ...
            // i = 19 1900-1999
            // i = 20 2000-2052
            // 总共才2053章
            int fromIndex = i * config.getSize();
            int toIndex = i == maxThreadSize - 1 ? chapters.size() : i * config.getSize() + config.getSize();
            downloadTaskAlloc.put(fromIndex + "-" + toIndex, chapters.subList(fromIndex, toIndex));
        }
        ExecutorService service = Executors.newFixedThreadPool(maxThreadSize);
        Set<String> keySet = downloadTaskAlloc.keySet();
        List<Future<String>> tasks = new ArrayList<Future<String>>();
        for (String key : keySet) {
            tasks.add(service.submit(new DownloadCallable(config.getPath() + "/" + key + ".txt", downloadTaskAlloc.get(key))));
        }
        service.shutdown();
        for (Future<String> future : tasks) {
            try {
                System.out.println(future.get() + "下载完成");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}

class DownloadCallable implements Callable<String> {
    private List<Chapter> chapters;
    private String path;
    public DownloadCallable(String path, List<Chapter> chapters) {
        this.path = path;
        this.chapters = chapters;
    }

    public String call() throws Exception {
        PrintWriter out = null;
        try {
            out = new PrintWriter(new File(path));
            for (Chapter chapter : chapters) {
                IChapterDetailSpider spider = ChapterDetailSpiderFactory.getChapterDetailSpider(chapter.getUrl());
                ChapterDetail detail = spider.getChapterDetail(chapter.getUrl());
                out.println(detail.getTitle());
                out.println(detail.getContent());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            out.close();
        }
        return path;
    }
}