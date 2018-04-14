package novel.storage;

import novel.storage.impl.BxwxNovelSpiderStorageImpl;
import novel.storage.impl.KanShuZhongNovelStorageImpl;

import java.io.FileNotFoundException;

/**
 * Created by Yang on 2018/4/13 0013.
 */
public class BootStrap {
    public static void main(String[] args) throws FileNotFoundException {
        Processor processor = new KanShuZhongNovelStorageImpl();
        processor.process();
        processor = new BxwxNovelSpiderStorageImpl();
        processor.process();
    }
}
