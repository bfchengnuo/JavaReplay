package com.bfchengnuo.designpattern.singleton;

/**
 * Created by 冰封承諾Andy on 2019/4/21.
 * 静态内部类方法实现
 */
public class StaticInternal {
    public static void main(String[] args) {
        System.out.println(StaticInternal.getInstance());
    }

    private StaticInternal() {
        System.out.println("初始化啦...");
    }

    private static class InternalClass{
        private static StaticInternal staticInternal = new StaticInternal();
    }

    /**
     * 当调用此方法时静态域 InternalClass 才会被加载初始化
     * @return
     */
    public static StaticInternal getInstance() {
        return InternalClass.staticInternal;
    }
}
