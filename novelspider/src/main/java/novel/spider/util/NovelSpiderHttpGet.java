package novel.spider.util;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;

import java.net.URI;

/**
 * Created by Yang on 2018/3/18 0018.
 */
public class NovelSpiderHttpGet extends HttpGet {
    public NovelSpiderHttpGet() {
    }

    public NovelSpiderHttpGet(URI uri) {
        super(uri);
    }

    public NovelSpiderHttpGet(String uri) {
        super(uri);
        this.setDefaultConfig();
    }

    // 设置默认请求参数
    private void setDefaultConfig() {
        this.setConfig(RequestConfig.custom()
                .setSocketTimeout(2000)
                .setConnectTimeout(10000)	//设置连接服务器的超时时间
                .setConnectionRequestTimeout(10000)	//设置从服务器读取数据的超时时间
                .build());
        this.setHeader("User-Agent", "NovelSpider");	//设置请求头
    }
}
