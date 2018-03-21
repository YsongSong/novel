package novel.spider.impl.abstracts;

import novel.spider.enums.NovelSiteEnum;
import novel.spider.util.NovelSpiderHttpGet;
import novel.spider.util.NovelSpiderUtil;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

/**
 * Created by Yang on 2018/3/12 0012.
 */
public abstract class AbstractSpider {
    /**
     * 抓取指定小说网站的内容
     * @param url
     * @return
     * @throws Exception
     */
    protected String crawl(String url) throws Exception{
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse httpResponse = null;
        try{
            httpClient = HttpClientBuilder.create().build();
            httpResponse = httpClient.execute(new NovelSpiderHttpGet(url));
            String result = EntityUtils.toString(httpResponse.getEntity(), NovelSpiderUtil.getContext(NovelSiteEnum.getEnumByUrl(url)).get("charset")); //将httpResponse响应的结果转换为string结果
            return result;
        }catch (Exception e){
            throw new RuntimeException(e);
        }finally {
            httpClient.close();
            httpResponse.close();
        }
    }
}
