package com.bfchengnuo.designpattern.factory.Simple;

/**
 * Created by 冰封承諾Andy on 5/3/2017.
 * 具体的产品角色之一；实现了抽象产品的接口
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
