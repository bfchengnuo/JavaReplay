package com.bfchengnuo.designpattern.adapter;

/**
 * Created by 冰封承諾Andy on 2017/9/15.
 */
public class ReliableBoy implements Boy {
    @Override
    public void wearClothes() {
        System.out.println("穿“合适”的衣服");
    }

    @Override
    public void eat() {
        System.out.println("男孩吃饭中....");
    }
}
