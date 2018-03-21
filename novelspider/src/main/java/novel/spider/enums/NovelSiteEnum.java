package novel.spider.enums;

/**
 * Created by Yang on 2018/3/11 0011.
 */
public enum NovelSiteEnum {
    DingDianXiaoShuo(1,"23wx.cc"),
    BiQuGe(2,"xs.la"),
    BiXiaWenXue(3,"bxwx9.org"),
    KanShuZhong(4,"kanshuzhong.com");
    private int id;
    private String url;
    private NovelSiteEnum(int id, String url) {
        this.id = id;
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public static NovelSiteEnum getEnumById(int id) {
        switch (id) {
            case 1 : return DingDianXiaoShuo;
            case 2 : return BiQuGe;
            case 3 : return BiXiaWenXue;
            case 4 : return KanShuZhong;
            default : throw new RuntimeException("id=" + id + "是不被支持的小说网站");
        }
    }

    public static NovelSiteEnum getEnumByUrl(String url) {
        for(NovelSiteEnum novelSiteEnum : values()) {
            if(url.contains(novelSiteEnum.getUrl())) {
                return novelSiteEnum;
            }
        }
        throw new RuntimeException("URL=" + url + "是不被支持的小说网站");
    }
}
