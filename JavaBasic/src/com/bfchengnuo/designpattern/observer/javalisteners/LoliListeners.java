package com.bfchengnuo.designpattern.observer.javalisteners;

import java.util.EventListener;

/**
 * Created by 冰封承諾Andy on 5/19/2017.
 * 事件监听器，使用 Java 提供的类
 * 需要继承 EventListener 是一个标记接口
 */
public interface LoliListeners extends EventListener {
    // 一个监听方法对应一个事件源中的需要被监听方法
    void doSpeak(Event event);
    void doHug(Event event);
}
