package com.bfchengnuo.designpattern.factory.Simple;

/**
 * Created by 冰封承諾Andy on 5/3/2017.
 * 用来调用测试
 */
public class Main {
    public static void main(String[] args) {
        // 调用工厂产生一个对象
        Loli stanLoli = LoliManager.factory("stan");
        stanLoli.hug();
        System.out.println(stanLoli.speak("bfchengnuo"));

        System.out.println("------------------------");

        Loli legLoli = LoliManager.factory("leg");
        legLoli.hug();
        System.out.println(legLoli.speak("bfchengnuo"));
    }
}
