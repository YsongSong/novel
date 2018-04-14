package novel.spider.entitys;

import java.io.Serializable;
import java.util.Date;

/**
 * 小说信息实体
 * Created by Yang on 2018/3/23 0023.
 */
public class Novel implements Serializable {
    private int id;
    private String name;    // 小说名(书名)
    private String author;  // 作者名
    private String url;     // 小说链接
    private String type;    // 小说类别
    private String lastUpdateChapter;   // 最后一章章节名
    private String lastUpdateChapterUrl;    // 最后一章链接
    private Date lastUpdateTime;    // 最后更新时间
    private int status;     // 小说状态(1:连载,2:完结)
    private char firstLetter;   // 书名的首字母
    private int platformId;     // 小说平台的id
    private String addTime;       // 这本小说存储到我们数据库的时间

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLastUpdateChapter() {
        return lastUpdateChapter;
    }

    public void setLastUpdateChapter(String lastUpdateChapter) {
        this.lastUpdateChapter = lastUpdateChapter;
    }

    public String getLastUpdateChapterUrl() {
        return lastUpdateChapterUrl;
    }

    public void setLastUpdateChapterUrl(String lastUpdateChapterUrl) {
        this.lastUpdateChapterUrl = lastUpdateChapterUrl;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public char getFirstLetter() {
        return firstLetter;
    }

    public void setFirstLetter(char firstLetter) {
        this.firstLetter = firstLetter;
    }

    public int getPlatformId() {
        return platformId;
    }

    public void setPlatformId(int platformId) {
        this.platformId = platformId;
    }

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Novel novel = (Novel) o;

        if (status != novel.status) return false;
        if (firstLetter != novel.firstLetter) return false;
        if (platformId != novel.platformId) return false;
        if (name != null ? !name.equals(novel.name) : novel.name != null) return false;
        if (author != null ? !author.equals(novel.author) : novel.author != null) return false;
        if (url != null ? !url.equals(novel.url) : novel.url != null) return false;
        if (type != null ? !type.equals(novel.type) : novel.type != null) return false;
        if (lastUpdateChapter != null ? !lastUpdateChapter.equals(novel.lastUpdateChapter) : novel.lastUpdateChapter != null)
            return false;
        if (lastUpdateChapterUrl != null ? !lastUpdateChapterUrl.equals(novel.lastUpdateChapterUrl) : novel.lastUpdateChapterUrl != null)
            return false;
        if (lastUpdateTime != null ? !lastUpdateTime.equals(novel.lastUpdateTime) : novel.lastUpdateTime != null)
            return false;
        return addTime != null ? addTime.equals(novel.addTime) : novel.addTime == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (lastUpdateChapter != null ? lastUpdateChapter.hashCode() : 0);
        result = 31 * result + (lastUpdateChapterUrl != null ? lastUpdateChapterUrl.hashCode() : 0);
        result = 31 * result + (lastUpdateTime != null ? lastUpdateTime.hashCode() : 0);
        result = 31 * result + status;
        result = 31 * result + (int) firstLetter;
        result = 31 * result + platformId;
        result = 31 * result + (addTime != null ? addTime.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Novel{" +
                "name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", url='" + url + '\'' +
                ", type='" + type + '\'' +
                ", lastUpdateChapter='" + lastUpdateChapter + '\'' +
                ", lastUpdateChapterUrl='" + lastUpdateChapterUrl + '\'' +
                ", lastUpdateTime=" + lastUpdateTime +
                ", status=" + status +
                ", firstLetter=" + firstLetter +
                ", platformId=" + platformId +
                ", addTime=" + addTime +
                '}';
    }
}
