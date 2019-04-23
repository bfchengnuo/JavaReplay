package com.bfchengnuo.designpattern.observer.simple;

/**
 * Created by 冰封承諾Andy on 5/3/2017.
 * 抽象目标(Subject)角色 也就是被抽象的被观察者
 * 定义对观察者的一系列操作，通俗理解就是主要定义三个功能的方法，注册、删除、更新（通知）
 */
public interface StandardLoli {
    //注册一个观察者
    void register(Lolicon observer);

    //移除一个观察者
    void remove(Lolicon observer);

    //通知所有观察者
    void notifyObservers(StandardLoliImpl data);

}
