package novel.spider.configuration;

import java.io.Serializable;

/**
 * Created by Yang on 2018/3/16 0016.
 */
public class Configuration implements Serializable {
    public static final int DEFAULT_SIZE = 100;     //默认每个线程可下载的最大章节数量
    /**
     * 每个线程下载每一章所允许的最大尝试次数
     */
    public static final int DEFAULT_TRY_TIMES = 3;

    private String path;    //下载后的文件保存的基地址：d:/opt/bxwx.org/完美世界/1-2.txt ...
    private int size;   //每个线程能够下载的最大章节数量
    private int tryTimes;

    /**
     * 即没有配置时size设置为默认值
     */
    public Configuration() {
        this.size = DEFAULT_SIZE;
        this.tryTimes = DEFAULT_TRY_TIMES;
    }
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getTryTimes() {
        return tryTimes;
    }
    public void setTryTimes(int tryTimes) {
        this.tryTimes = tryTimes;
    }
}
