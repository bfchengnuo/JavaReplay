package com.bfchengnuo.designpattern.observer.javalisteners;

/**
 * Created by 冰封承諾Andy on 5/19/2017.
 * 测试类...
 * 使用 Java 提供的类与接口
 */
public class Test {
    public static void main(String[] args) {
        // 事件源
        StandardLoli loli = new StandardLoli();

        // 注册监听器
        loli.registerListeners(new MyListeners());

        // test
        loli.speak("bfchengnuo");
        System.out.println("----------------");
        loli.hug();
    }
}
