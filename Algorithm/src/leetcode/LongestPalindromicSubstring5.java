package leetcode;

/**
 * Created by 冰封承諾Andy on 2019/3/5.
 * <p>
 * 最长回文子串
 * https://leetcode-cn.com/problems/longest-palindromic-substring/
 */
public class LongestPalindromicSubstring5 {
    public static void main(String[] args) {
        System.out.println(longestPalindrome("abcba"));
        System.out.println(longestPalindrome("bananas"));
        System.out.println(longestPalindrome("a"));
        System.out.println(longestPalindrome("ac"));
        System.out.println(longestPalindrome("abc"));
        System.out.println(longestPalindrome("babad"));
        System.out.println(longestPalindrome("cbbd"));
    }


    public static String longestPalindrome(String s) {
        if (s.length() <= 1) {
            return s;
        }
        String finalStr = "";
        int left = 0;
        int i = 1;
        int mid;
        while (left < s.length() - 1 && (s.length() - left) > finalStr.length()) {
            if (s.charAt(left) == s.charAt(i)) {
                // 是否是回文串
                mid = (i + left) / 2;
                // 偶数处理，需要 + 1
                if ((i - left + 1) % 2 == 0) {
                    mid++;
                }
                // if (s.substring(left, mid).contentEquals(new StringBuilder(s.substring((i + left) / 2 + 1, i + 1)).reverse())) {
                if (isPalindrome(s.substring(left, mid), s.substring((i + left) / 2 + 1, i + 1))) {
                    if (finalStr.length() < i - left + 1) {
                        finalStr = s.substring(left, i + 1);
                    }
                }
            }

            if (i == s.length() - 1) {
                left++;
                i = left + 1;
            } else {
                i++;
            }
        }
        return finalStr.length() == 0 ? String.valueOf(s.charAt(0)) : finalStr;
    }

    private static boolean isPalindrome(String s1, String s2) {
        int lenIndex = s1.length() - 1;
        for (int i = 0; i <= lenIndex; i++) {
            if (s1.charAt(i) != s2.charAt(lenIndex - i)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 中心扩展算法
     *
     * @param s
     * @return
     */
    public static String longestPalindromeOfficial(String s) {
        if (s == null || s.length() < 1) return "";
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            // 分别对应偶数和奇数的情况
            int len1 = expandAroundCenter(s, i, i);
            int len2 = expandAroundCenter(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    private static int expandAroundCenter(String s, int left, int right) {
        int L = left, R = right;
        while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
            L--;
            R++;
        }
        return R - L - 1;
    }
}
