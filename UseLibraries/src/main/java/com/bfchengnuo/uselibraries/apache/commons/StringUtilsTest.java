package com.bfchengnuo.uselibraries.apache.commons;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * @author Created by 冰封承諾Andy on 2019/5/8.
 * <p>
 * 由于 StringUtils 拥有 100+ 的方法，我们只能选部分常用的来说
 */
@SuppressWarnings("ALL")
public class StringUtilsTest {
    public static void main(String[] args) {
        String str = "Hello World";

        // isEmpty 和 isBlank 的区别在于：
        // isEmpty 不会忽略空格；
        // isBlank 会认为是空, isBlank 更常用
        StringUtils.isEmpty(str);
        StringUtils.isNotEmpty(str);
        StringUtils.isBlank(str);
        StringUtils.isNotBlank(str);


        // strip      --> 去除两端的 aa
        // stripStart --> 去除开始位置的 hell
        // stripEnd   --> 去除结尾位置的 orld
        StringUtils.strip(str, "aa");
        StringUtils.stripStart(str, "hell");
        StringUtils.stripEnd(str, "orld");


        // 返回字符串在第三次出现 A 的位置
        StringUtils.ordinalIndexOf(str, "A", 3);


        // 获取 str 开始为 hello 结尾为 orld 中间的字符串
        // 注意此方法返回字符串      -->substringBetween
        // 注意此方法返回字符串数组(多了个s) --> substringsBetween
        StringUtils.substringBetween(str, "hell", "orld");
        StringUtils.substringsBetween(str, "hell", "orld");


        // 重复（拼接）字符串,第二种重载形式为在重复中用 hahah 拼接
        StringUtils.repeat(str, 3);
        StringUtils.repeat(str, "hahah", 2);


        // 统计参数 H 在字符串中出现的次数
        System.out.println(StringUtils.countMatches(str, "H"));


        // 判断字符串是否全小写或大写
        StringUtils.isAllLowerCase(str);
        StringUtils.isAllUpperCase(str);


        // isAlpha        --> 全部由字母组成返回true
        // isNumeric      -->全部由数字组成返回true
        // isAlphanumeric -->全部由字母或数字组成返回true
        // isAlphaSpace   -->全部由字母或空格组成返回true
        // isWhitespace   -->全部由空格组成返回true
        StringUtils.isAlpha(str);
        StringUtils.isNumeric(str);
        StringUtils.isAlphanumeric(str);
        StringUtils.isAlphaSpace(str);
        StringUtils.isWhitespace(str);


        // 缩进字符串,第二参数最低为 4,要包含...
        // 现在 Hello World 输出为 H...
        System.out.println(StringUtils.abbreviate(str, 4));


        // 首字母大写或小写
        StringUtils.capitalize(str);
        StringUtils.uncapitalize(str);


        // 将字符串数组转变为一个字符串,通过 "," 拼接, 支持传入 iterator 和 collection
        StringUtils.join(new String[]{"Hello", "World"}, ",");

        /*
         * 经常性要把后台的字符串传递到前台作为 html 代码进行解释,
         * 可以使用以下方法进行转换,以下方法输出为
         * <p>Hello</p>
         *
         * 已废弃，推荐使用 commons-text 库
         */
        StringEscapeUtils.escapeHtml4("Hello");
    }
}
