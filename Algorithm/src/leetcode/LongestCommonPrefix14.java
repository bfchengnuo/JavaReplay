package leetcode;

/**
 * Created by 冰封承諾Andy on 2019/3/6.
 * <p>
 * 最长公共前缀
 * https://leetcode-cn.com/problems/longest-common-prefix/
 */
public class LongestCommonPrefix14 {
    public static void main(String[] args) {
        System.out.println(longestCommonPrefix(new String[]{"flower", "flow", "flight"}));
        System.out.println(longestCommonPrefix(new String[]{"dog", "racecar", "car"}));

        System.out.println("--------");
    }

    public static String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) {
            return "";
        }
        /*String minStr = strs[0];
        for (String str : strs) {
            if (minStr.length() > str.length()) {
                minStr = str;
            }
        }*/

        String minStr = strs[0];
        for (int i = 1; i < strs.length; i++) {
            if (!strs[i].startsWith(minStr)) {
                if (minStr.length() == 1) {
                    minStr = "";
                    break;
                }
                minStr = minStr.substring(0, minStr.length() - 1);
                i = 0;
            }
        }

        return minStr;
    }
}
