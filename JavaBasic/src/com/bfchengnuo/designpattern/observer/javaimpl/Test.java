package com.bfchengnuo.designpattern.observer.javaimpl;

/**
 * Created by 冰封承諾Andy on 5/18/2017.
 * Java 中类实现方式的测试类
 */
public class Test {
    public static void main(String[] args) {
        // 创建被观察者
        StandardLoli loli = new StandardLoli();

        // 创建观察者
        Lolicon lolicon1 = new Lolicon();
        Lolicon lolicon2 = new Lolicon("飞奔而来!!");
        Lolicon lolicon3 = new Lolicon("从天而降!!");

        // 注册观察者，顺序是先加入的后执行
        loli.addObserver(lolicon1);
        loli.addObserver(lolicon2);
        loli.addObserver(lolicon3);

        // 更新被观察者状态
        loli.speak("bfchengnuo");
    }
}
