package com.bfchengnuo.designpattern.adapter;

/**
 * Created by 冰封承諾Andy on 2017/9/15.
 * 装饰者....伪装起来！
 */
public class BoyAdapter implements Girl {
    private Boy boy;

    public BoyAdapter(Boy boy) {
        this.boy = boy;
    }

    @Override
    public void wearDress() {
        System.out.println("适配/转换中....");
        boy.wearClothes();
    }

    @Override
    public void eat() {
        boy.eat();
    }

    @Override
    public void prayer() {
        System.out.println("我不会....");
    }
}
