package com.bfchengnuo.designpattern.observer.simple;

/**
 * Created by 冰封承諾Andy on 5/18/2017.
 * 抽象观察者(Observer)角色
 * 通常只有一个方法，就是更新
 * 当被观察者发生更新后，调用此方法使得观察者进行自己的更新
 */
public interface Lolicon {
    // 更新的时候可以接受一个被观察者对象，用于获取相关信息
    // 这里直接传 StandardLoliImpl 了
    void update(StandardLoliImpl loli);
}
