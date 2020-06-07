package com.bfchengnuo.basic;

/**
 * 学 Go 学的有点混。。。
 *
 * @author 冰封承諾Andy
 * @date 2020/5/11
 */
public class Scope {
    private static String name = "Mps";

    public static void main(String[] args) {
        System.out.println(name);
        String name = "skye";
        System.out.println(name);
    }
}
