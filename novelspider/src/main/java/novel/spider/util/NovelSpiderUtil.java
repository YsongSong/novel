package novel.spider.util;

import novel.spider.enums.NovelSiteEnum;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Yang on 2018/3/11 0011.
 */
public final class NovelSpiderUtil {
    private static final Map<NovelSiteEnum, Map<String, String>> CONTEXT_MAP = new HashMap<NovelSiteEnum, Map<String, String>>();
    static {
        init();
    }
    private NovelSpiderUtil() {}

    private static void init() {
        SAXReader reader = new SAXReader();
        try {
            Document doc = reader.read(new File("conf/Spider-Rule.xml"));  //生成指定文档的实体
            Element root = doc.getRootElement();   //获取根节点
            List<Element> sites = root.elements("site");   //sites节点
            //遍历sites节点
            for (Element site : sites) {
                List<Element> subs = site.elements();   //子节点
                Map<String, String> subMap = new HashMap<String, String>();
                //遍历site节点
                for (Element sub : subs) {
                    String name = sub.getName();
                    String text = sub.getTextTrim();
                    subMap.put(name, text);
                }
                CONTEXT_MAP.put(NovelSiteEnum.getEnumByUrl(subMap.get("url")), subMap);
            }
        } catch (Exception e) {
            throw new RuntimeException("配置文件加载错误");
        }
    }

    /**
     * 拿到对应网站的解析规则
     */
    public static Map<String, String> getContext(NovelSiteEnum novelSiteEnum) {
        return CONTEXT_MAP.get(novelSiteEnum);
    }

    /**
     *  多个文件合并为一个文件，合并规则：按文件名分割排序
     * @param path  基础目录，该目录下的所有文本文件都会被合并到mergeToFile
     * @param mergeToFile   被合并的文本文件，这个参数可以为null，合并后的文件保存在path/merge.txt
     */
    public static void mulitFileMerge(String path, String mergeToFile, boolean deleteThisFile) {
        mergeToFile = mergeToFile == null ? path + "/merge.txt" : mergeToFile;
        File[] files = new File(path).listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".txt");
            }
        });
        //  按文件名排序
        Arrays.sort(files, new Comparator<File>() {
            @Override
            public int compare(File o1, File o2) {
                int o1Index = Integer.parseInt(o1.getName().split("\\-")[0]);
                int o2Index = Integer.parseInt(o2.getName().split("\\-")[0]);
                if(o1Index > o2Index) {
                    return 1;
                } else if(o1Index == o2Index) {
                    return 0;
                } else {
                    return -1;
                }
            }
        });
        PrintWriter out = null;
        try {
            out = new PrintWriter(new File(mergeToFile), "UTF-8");
            for (File file : files) {
                BufferedReader buffer = new BufferedReader(new InputStreamReader(new FileInputStream(file),"UTF-8"));
                String line = null;
                while ((line = buffer.readLine()) != null) {
                    out.println(line);
                }
                buffer.close();
                if(deleteThisFile) {
                    file.delete();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            out.close();
        }
    }

    /**
     * 格式化日期字符串为日期对象
     * @param dateStr
     * @param pattern
     * @return
     * @throws ParseException
     */
    public static Date getDate(String dateStr, String pattern) throws ParseException {
        if ("MM-dd".equals(pattern)) {
            pattern = "yyyy-MM-dd";
            dateStr = getDateField(Calendar.YEAR) + "-" + dateStr;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        Date date = sdf.parse(dateStr);
        return date;
    }

    /**
     * 获取本时刻的字符量
     * @param field
     * @return
     */
    public static String getDateField(int field) {
        Calendar cal = new GregorianCalendar();
        return cal.get(field) + "";
    }

    /**
     * 获取书籍的状态
     * @param status
     * @return
     */
    public static int getNovelStatus(String status) {
        if (status.contains("连载")) {
            return 1;
        } else if (status.contains("完结") || status.contains("完成") || status.contains("完本")) {
            return 2;
        } else {
            throw new RuntimeException ("不支持的书籍状态：" + status);
        }
    }
}
