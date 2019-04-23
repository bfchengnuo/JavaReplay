package com.bfchengnuo.designpattern.observer.listeners;

/**
 * Created by 冰封承諾Andy on 5/19/2017.
 * 事件源的监听器的接口，也就是代表着事件监听器
 */
public interface LoliListeners {
    // 一个监听方法对应一个事件源中的需要被监听方法
    void speakListeners(Event event);
    void hugListeners(Event event);


    // doSpeak()  命名
}
