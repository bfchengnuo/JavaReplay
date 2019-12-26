package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 括号生成
 * <p>
 * https://leetcode-cn.com/problems/generate-parentheses/
 *
 * @author Created by 冰封承諾Andy on 2019/12/25.
 */
public class GenerateParentheses22 {
    public static void main(String[] args) {
        System.out.println(generateParenthesis(3));
    }

    public static List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        generate(res, "", 0, 0, n);

        return res;
    }

    /**
     * count1统计“(”的个数，count2统计“)”的个数
     *
     * @param res 结果集
     * @param ans 结果项
     * @param count1 （ 的个数
     * @param count2 ） 的个数
     * @param n 括号对数
     */
    public static void generate(List<String> res, String ans, int count1, int count2, int n) {
        // System.out.println(ans);
        if (count1 > n || count2 > n) {
            return;
        }

        if (count1 == n && count2 == n) {
            res.add(ans);
        }

        if (count1 >= count2) {
            generate(res, ans + "(", count1 + 1, count2, n);
            generate(res, ans + ")", count1, count2 + 1, n);

        }
    }
}
