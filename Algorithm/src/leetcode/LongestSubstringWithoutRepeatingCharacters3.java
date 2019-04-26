package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 冰封承諾Andy on 2019/3/3.
 * <p>
 * 无重复字符的最长子串
 * https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/
 */
public class LongestSubstringWithoutRepeatingCharacters3 {
    public static void main(String[] args) {

        System.out.println(lengthOfLongestSubstring("abcabcbb")); // 3
        System.out.println(lengthOfLongestSubstring("bbbbb"));  // 1
        System.out.println(lengthOfLongestSubstring("pwwkew"));  // 3
        System.out.println(lengthOfLongestSubstring(" "));  // 1
        System.out.println(lengthOfLongestSubstring("dvdf"));  // 3
    }

    public static int lengthOfLongestSubstring(String s) {
        if ("".equals(s)) {
            return 0;
        }
        char[] chars = s.toCharArray();
        StringBuilder sb = new StringBuilder(s.length());
        int left = 0;
        int sum = 0;
        sb.append(chars[0]);
        for (int i = 1; i < chars.length; i++) {
            if (sb.indexOf(String.valueOf(chars[i])) == -1) {
                sb.append(chars[i]);
            } else {
                sum = Math.max(sum, sb.length());
                sb.delete(0, sb.length());
                i = ++left;
                sb.append(chars[left]);
            }
        }
        return Math.max(sum, sb.length());
    }


    /**
     * 滑动窗口
     *
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstringOfficial(String s) {
        int size = s.length(), ans = 0;
        // current index of character
        Map<Character, Integer> map = new HashMap<>();
        // try to extend the range [i, j]
        for (int j = 0, i = 0; j < size; j++) {
            // 如果存在，向前滑动窗口
            if (map.containsKey(s.charAt(j))) {
                i = Math.max(map.get(s.charAt(j)), i);
            }
            // 不存在，继续扩展 j，并记录最大长度
            ans = Math.max(ans, j - i + 1);
            map.put(s.charAt(j), j + 1);
        }
        return ans;
    }

    /**
     * 以前的我们都没有对字符串 s 所使用的字符集进行假设。
     * <p>
     * 当我们知道该字符集比较小的时侯，我们可以用一个整数数组作为直接访问表来替换 Map。
     *
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstringASCIIOfficial(String s) {
        int size = s.length(), ans = 0;
        // current index of character
        int[] index = new int[128];
        // try to extend the range [i, j]
        for (int j = 0, i = 0; j < size; j++) {
            i = Math.max(index[s.charAt(j)], i);
            ans = Math.max(ans, j - i + 1);
            index[s.charAt(j)] = j + 1;
        }
        return ans;
    }
}
