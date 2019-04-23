package com.bfchengnuo.designpattern.observer.simple;

/**
 * Created by 冰封承諾Andy on 5/18/2017.
 * 具体观察者(ConcreteObserver)角色
 * 被观察者更新后会被调用，然后更新自己的状态
 */
public class LoliconImpl implements Lolicon {
    private String status = "我来了！！！";

    // 当被观察者更新时会被调用
    @Override
    public void update(StandardLoliImpl loli) {
        // 更新自己.....
        System.out.println(status + loli.getName());
    }
}
