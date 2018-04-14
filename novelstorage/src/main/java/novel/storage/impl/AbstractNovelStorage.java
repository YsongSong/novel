package novel.storage.impl;

import novel.spider.entitys.Novel;
import novel.spider.interfaces.INovelSpider;
import novel.spider.util.NovelSpiderFactory;
import novel.spider.util.NovelSpiderHttpGet;
import novel.spider.util.NovelSpiderUtil;
import novel.storage.Processor;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.Map.Entry;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by Yang on 2018/4/12 0012.
 */
public abstract class AbstractNovelStorage implements Processor {
    protected SqlSessionFactory sqlSessionFactory;
    protected Map<String, String> tasks = new TreeMap<String, String>();
    public AbstractNovelStorage() throws FileNotFoundException {
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(new FileInputStream("conf/SqlMapConfig.xml"));
    }
    public void process() {
        final ExecutorService service = Executors.newFixedThreadPool(tasks.size());
        List<Future<String>> futures = new ArrayList<Future<String>>(tasks.size());
        for (Entry<String, String> entry : tasks.entrySet()) {
            final String key = entry.getKey();
            final String value = entry.getValue();
            futures.add(service.submit(new Callable<String>() {
                public String call() throws Exception {
                    INovelSpider spider = NovelSpiderFactory.getNovelSpider(value);
                    Iterator<List<Novel>> iterator = spider.iterator(value, 10);
                    Date date = new Date();
                    String nowdate = NovelSpiderUtil.formatDate(date, "yyyy-MM-dd");
                    while (iterator.hasNext()) {
                        System.out.println("开始抓取[" + key + "]的URL" + spider.next());
                        List<Novel> novels = iterator.next();
                        for (Novel novel : novels) {
                            novel.setFirstLetter(key.charAt(0));    //设置小说的名字的首字母
                            novel.setAddTime(nowdate);
                        }
                        SqlSession session = sqlSessionFactory.openSession();
                        session.insert("batchInsert", novels);
                        session.commit();
                        session.close();
                        Thread.sleep(1000);
                    }
                    return key;
                }
            }));
        }
        service.shutdown();
        for(Future<String> future : futures) {
            try {
                System.out.println("抓取[" + future.get() + "]结束！");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
