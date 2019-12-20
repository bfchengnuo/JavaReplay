package leetcode;

/**
 * 字符串转换整数 (atoi)
 * https://leetcode-cn.com/problems/string-to-integer-atoi/
 *
 * @author Created by 冰封承諾Andy on 2019/12/19.
 */
public class StringToIntegerAtoi8 {

    public static void main(String[] args) {
        System.out.println(myAtoi("-5-"));
        System.out.println(myAtoi("+aa1"));
        System.out.println(myAtoi("0-1"));
        System.out.println(myAtoi("  0000000000012345678"));
        System.out.println(myAtoi("+-"));
        System.out.println(myAtoi("    +"));
        System.out.println(myAtoi("words and 987"));
        System.out.println(myAtoi("-123www"));
        System.out.println(myAtoi("       12  3"));
        System.out.println(myAtoi("       -91283472332"));
    }

    public static int myAtoi(String str) {
        if (str == null) {
            return 0;
        }

        str = str.trim();

        if (str.length() == 0) {
            return 0;
        }

        char[] nums = str.trim().toCharArray();
        int l = 0;
        int r = str.length();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == '+' || nums[i] == '-') {
                // 第一个不为符号直接返回结束坐标
                if (i != 0) {
                    r = i;
                    break;
                }
                // 只有一位符号，直接返回 0
                if (nums.length == 1) {
                    l = -1;
                    break;
                }
                continue;
            }

            if (nums[i] >= 48 && nums[i] <= 57) {
                continue;
            }
            r = i;
            break;
        }

        if (l == -1 || r == 0) {
           return 0;
        }

        String finalStr = str.substring(l, r);
        // 只有一个符号的情况
        if (finalStr.length() == 1 && (nums[l] == '+' || nums[l] == '-')) {
            return 0;
        }
        try {
            return Integer.parseInt(finalStr);
        } catch (NumberFormatException e) {
            if (nums[0] == '-') {
                return Integer.MIN_VALUE;
            }
             return Integer.MAX_VALUE;
        }
    }
}
