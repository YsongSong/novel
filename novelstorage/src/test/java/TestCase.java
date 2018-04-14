import novel.spider.enums.NovelSiteEnum;
import novel.storage.Processor;
import novel.storage.impl.BxwxNovelSpiderStorageImpl;
import novel.storage.impl.KanShuZhongNovelStorageImpl;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Created by Yang on 2018/4/12 0012.
 */
public class TestCase {

    @Test
    public void testMyBatisConnection() throws FileNotFoundException {
        SqlSession session = new SqlSessionFactoryBuilder().build(new FileInputStream("conf/SqlMapConfig.xml")).openSession();
        System.out.println(session);

    }

    @Test
    public void getCon() {
        System.out.println(NovelSiteEnum.getEnumByUrl("https://www.bxwx9.org/"));
    }

    /**
     * 看书中小说存储
     * @throws FileNotFoundException
     */
    @Test
    public void testSaveKanShuZhong() throws FileNotFoundException {
        Processor storage = new KanShuZhongNovelStorageImpl();
        storage.process();
    }

    /**
     * 笔下文学小说存储
     * @throws FileNotFoundException
     */
    @Test
    public void testBxwxStorage() throws FileNotFoundException {
        Processor storage = new BxwxNovelSpiderStorageImpl();
        storage.process();
    }
}
