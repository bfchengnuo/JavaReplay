package com.bfchengnuo.designpattern.command;

/**
 * Created by 冰封承諾Andy on 2017/9/14.
 * 接受者对象，具体执行的对象
 * 随便写写
 */
public class Light {
    private String local;

    public Light(String local) {
        this.local = local;
    }

    public void on() {
        System.out.println(local + "灯已经打开！");
    }

    public void off() {
        System.out.println(local + "灯已经关闭！");
    }
}
