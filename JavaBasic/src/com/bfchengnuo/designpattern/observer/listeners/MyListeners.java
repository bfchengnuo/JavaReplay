package com.bfchengnuo.designpattern.observer.listeners;

/**
 * Created by 冰封承諾Andy on 5/19/2017.
 * 事件源的监听器，也就是事件监听器
 * 感觉像是观察者
 */
public class MyListeners implements LoliListeners {
    @Override
    public void speakListeners(Event event) {
        StandardLoli loli = event.getStandardLoli();
        System.out.println(loli.getName() + "我来了！！");
    }

    @Override
    public void hugListeners(Event event) {
        System.out.println("抱抱..");
    }
}
