package com.bfchengnuo.java11;

import java.util.Optional;

/**
 * 新的 string API
 *
 * @author 冰封承諾Andy
 * @date 2020/5/15
 */
public class StringApi {
    public static void main(String[] args) {
        System.out.println(" ".isBlank());
        System.out.println("A\nB\nC".lines().count());
        System.out.println("Java".repeat(3));
        // 类似 trim，区别是 适用于字符首尾空白是 Unicode 空白字符的情况
        // trim() 方法无法删除掉 Unicode 空白字符
        System.out.println(" Foo Bar ".strip());
        System.out.println(" Foo Bar ".stripLeading());
        System.out.println(" Foo Bar ".stripTrailing());
        // Unicode 空白符，或者中文全角的空格，trim 无法去除，而 strip 可以
        Character c = '\u2000';
        var str = c + "hello" + c;
        System.out.println(str.trim().equals(str.strip()));
    }

    public static void optional() {
        // 新增 API, 主要
        Optional<Object> opt = Optional.ofNullable(null);
        System.out.println(opt.orElse("empty"));
    }
}
