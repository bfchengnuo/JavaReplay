package com.bfchengnuo.designpattern.strategy;

/**
 * Created by 冰封承諾Andy on 5/3/2017.
 * 实现接口，也就是具体的策略类
 */
public class LegitimateLoli implements Loli {
    @Override
    public String speak(String name) {
        return "(￣^￣)" + name;
    }

    @Override
    public void hug() {
        System.out.println("(づ｡◕‿‿◕｡)づ");
    }
}
