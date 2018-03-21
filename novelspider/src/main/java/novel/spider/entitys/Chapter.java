package novel.spider.entitys;

import java.io.Serializable;

/**
 * Created by Yang on 2018/3/11 0011.
 * 小说章节实体
 */
public class Chapter implements Serializable{
    private String title;
    private String url;

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Chapter chapter = (Chapter) o;

        if (title != null ? !title.equals(chapter.title) : chapter.title != null) return false;
        return url != null ? url.equals(chapter.url) : chapter.url == null;
    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (url != null ? url.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Chapter{" +
                "title='" + title + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
