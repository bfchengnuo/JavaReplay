package com.bfchengnuo.uselibraries.guava;

import com.google.common.base.CharMatcher;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;

import java.util.ArrayList;

/**
 * Spring 处理相关的工具类
 *
 * @author Created by 冰封承諾Andy on 2019/6/9.
 */
public class StringRelated {

    /**
     * 连接器，主要方便处理连接过程中的 null
     * <p>
     * useForNull(String) 替换 null 的默认值
     * skipNulls 忽略 null 值
     */
    public static void joinerTest() {
        Joiner joiner = Joiner.on("; ").skipNulls();
        String str = joiner.join("Harry", null, "Ron", "Hermione");
        System.out.println(str);
    }

    /**
     * 拆分器，方便处理 JDK 的 split 末尾问题
     * <p>
     * splitter 实例总是不可变的，这使得 splitter 实例都是线程安全的，你可以将其定义为 static final 常量。
     */
    public static void splitterTest() {
        ArrayList<String> strings = Lists.newArrayList(
                Splitter.on(',')
                        // 移除结果字符串的前导空白和尾部空白
                        .trimResults()
                        // 从结果中自动忽略空字符串
                        .omitEmptyStrings()
                        .split("foo,bar,,   qux,,")
        );

        System.out.println(strings);
    }

    /**
     * 字符串匹配器
     */
    public static void charMatcherTest(String str) {
        // 移除 control 字符
        String noControl = CharMatcher.javaIsoControl().removeFrom(str);
        // 只保留数字字符，使用 or() 可以追加条件
        String theDigits = CharMatcher.inRange('0', '9').retainFrom(str);
        String spaced = CharMatcher.whitespace().trimAndCollapseFrom(str, ' ');
        // 去除两端的空格，并把中间的连续空格替换成单个空格
        // 用*号替换所有数字
        String noDigits = CharMatcher.inRange('0', '9').replaceFrom(str, "*");

        System.out.println(theDigits);
        System.out.println(noDigits);
    }
}
