package com.bfchengnuo.designpattern.adapter;

/**
 * Created by 冰封承諾Andy on 2017/9/15.
 */
public class LovelyGirl implements Girl {
    @Override
    public void wearDress() {
        System.out.println("穿女装 o(*≧▽≦)ツ");
    }

    @Override
    public void eat() {
        System.out.println("少女进食中....");
    }

    @Override
    public void prayer() {
        System.out.println("少女祈祷中...");
    }
}
