package com.bfchengnuo.uselibraries.apache.commons;

import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.math.NumberUtils;

/**
 * @author Created by 冰封承諾Andy on 2019/5/8.
 *
 * NumberUtils 与 RandomUtils
 */
public class NumberUtilsTest {
    public static void main(String[] args) {
        String str = "12.7";

        // 判断字符串是否为整数
        System.out.println("是否整数：" + NumberUtils.isDigits(str));

        // 判断字符串是否为数字 isNumber 已过时
        // NumberUtils.isNumber(str);
        System.out.println("str 是否为数字：" + NumberUtils.isCreatable(str));

        // 将字符串转换为int类型, 内部调用 Integer.parseInt 增加了个默认值
        System.out.println("toInt：" + NumberUtils.toInt("12", 0));

        // 获取参数中最大的值,支持传入数组
        System.out.println("Max：" + NumberUtils.max(10, 20, 30));

        // 获取参数中最小的值,支持传入数组
        System.out.println("Min：" + NumberUtils.min(10, 20, 30));

        // 通过字符串创建BigDecimal类型 ,支持int,float,long等数值
        System.out.println(NumberUtils.createBigDecimal(str));

        randomUtilsTest();
    }

    /**
     * RandomUtils 帮助我们产生随机数,不止是数字类型 ,
     * 连 boolean 类型都可以通过 RandomUtils 产生
     */
    private static void randomUtilsTest() {
        System.out.println(RandomUtils.nextBoolean());
        System.out.println(RandomUtils.nextDouble());
        System.out.println(RandomUtils.nextLong());
        // 注意这里传入的参数不是随机种子,而是在0~1000之间产生一位随机数
        System.out.println(RandomUtils.nextInt(100, 1000));
    }
}
