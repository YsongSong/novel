package novel.spider.util;

/**
 * @ClassName ChapterDetailUtil
 * @Description TODO
 * @Author Yang Song
 * @Date 2019/6/22 13:34
 */
public class ChapterDetailUtil {

    /**
     * 处理元素的下标索引
     * @param splits
     * @return
     */
    public static String[] pareseSelector(String[] splits) {
        String[] newSplits = new String[2];
        //  判断数组长度是否为2，是返回数组，否返回新数组(原数组值为第一个值，第二个值设为0)
        if(splits.length == 1) {
            newSplits[0] = splits[0];
            newSplits[1] = "0";
            return newSplits;
        } else {
            return splits;
        }
    }
}
