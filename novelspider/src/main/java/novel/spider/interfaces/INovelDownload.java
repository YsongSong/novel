package novel.spider.interfaces;

import novel.spider.configuration.Configuration;

/**
 * 小说下载
 * Created by Yang on 2018/3/14 0014.
 */
public interface INovelDownload {
    /**
     * 小说下载
     * @param url (这个url是指某本小说的章节列表页面)
     * @return (比如说我下载到D:/novel/biquge.tw/完美世界/完美世界.txt)
     */
    public String download(String url, Configuration config);
}
