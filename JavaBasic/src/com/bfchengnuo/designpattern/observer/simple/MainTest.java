package com.bfchengnuo.designpattern.observer.simple;

/**
 * Created by 冰封承諾Andy on 5/18/2017.
 * 观察者模式的测试类
 */
public class MainTest {
    public static void main(String[] args) {
        // 创建被观察者
        StandardLoliImpl standardLoli = new StandardLoliImpl();

        // 创建观察者
        LoliconImpl lolicon = new LoliconImpl();

        // 将观察者注册到被观察者上
        standardLoli.register(lolicon);

        // 更新被观察者，观察者也会自动更新自己的状态
        standardLoli.speak("bfchengnuo");
    }
}
