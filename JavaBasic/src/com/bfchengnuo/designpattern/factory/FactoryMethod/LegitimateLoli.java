package com.bfchengnuo.designpattern.factory.FactoryMethod;

/**
 * Created by 冰封承諾Andy on 5/3/2017.
 * 具体的产品角色之一；实现了抽象产品的接口
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
