package com.bfchengnuo.designpattern.strategy;

/**
 * Created by 冰封承諾Andy on 5/3/2017.
 * 实现接口，也就是具体的策略类
 */
public class StandardLoli implements Loli {
    @Override
    public String speak(String name) {
        return name + "大哥哥";
    }

    @Override
    public void hug() {
        System.out.println("要抱抱");
    }
}
