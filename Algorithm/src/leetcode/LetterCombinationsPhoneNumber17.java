package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 冰封承諾Andy on 2019/3/26.
 * <p>
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
 * 老版手机键盘
 * <p>
 * 输入："23"
 * 输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 * <p>
 * https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number/
 */
public class LetterCombinationsPhoneNumber17 {

    public static void main(String[] args) {
        letterCombinations("23").forEach(System.out::println);
        letterCombinations("7").forEach(System.out::println);
    }

    public static List<String> letterCombinations(String digits) {
        List<String> temp = new ArrayList<>();
        List<String> result = new ArrayList<>();
        for (int i = 0; i < digits.length(); i++) {
            switch (digits.charAt(i)) {
                case '2':
                    preList(result, temp, "abc");
                    break;
                case '3':
                    preList(result, temp, "def");
                    break;
                case '4':
                    preList(result, temp, "ghi");
                    break;
                case '5':
                    preList(result, temp, "jkl");
                    break;
                case '6':
                    preList(result, temp, "mno");
                    break;
                case '7':
                    preList(result, temp, "pqrs");
                    break;
                case '8':
                    preList(result, temp, "tuv");
                    break;
                case '9':
                    preList(result, temp, "wxyz");
                    break;
            }
        }

        return result;
    }

    private static void preList(List<String> result, List<String> temp, String data) {
        if (temp.size() == 0) {
            temp.add(String.valueOf(data.charAt(0)));
            temp.add(String.valueOf(data.charAt(1)));
            temp.add(String.valueOf(data.charAt(2)));
            if (data.length() == 4) {
                temp.add(String.valueOf(data.charAt(3)));
            }
            result.addAll(temp);
            return;
        }

        result.clear();
        temp.forEach(str -> {
            result.add(str + data.charAt(0));
            result.add(str + data.charAt(1));
            result.add(str + data.charAt(2));
            if (data.length() == 4) {
                result.add(str + data.charAt(3));
            }
        });
        temp.clear();
        temp.addAll(result);
    }


    public static List<String> letterCombinationsFinal(String digits) {
        List<String> list = new ArrayList<>();
        String[] s = new String[digits.length()];
        if (s.length == 0) {
            return list;
        }

        for (int i = 0; i < digits.length(); i++) {
            switch (digits.charAt(i)) {
                case '2':
                    s[i] = "abc";
                    break;
                case '3':
                    s[i] = "def";
                    break;
                case '4':
                    s[i] = "ghi";
                    break;
                case '5':
                    s[i] = "jkl";
                    break;
                case '6':
                    s[i] = "mno";
                    break;
                case '7':
                    s[i] = "pqrs";
                    break;
                case '8':
                    s[i] = "tuv";
                    break;
                case '9':
                    s[i] = "wxyz";
                    break;
            }
        }
        list = getStringWithFor(s, 0, list, "");
        return list;
    }

    private static List<String> getStringWithFor(String[] s, int i, List<String> list, String stemp) {
        if (i < s.length - 1) {
            for (int j = 0; j < s[i].length(); j++) {
                list = getStringWithFor(s, i + 1, list, stemp + s[i].charAt(j));
            }
        } else {
            for (int j = 0; j < s[i].length(); j++) {
                list.add(stemp + s[i].charAt(j));
            }
        }

        return list;
    }
}
