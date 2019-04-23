package com.bfchengnuo.designpattern.singleton;

/**
 * Created by 冰封承諾Andy on 2019/4/21.
 * 单例模式的实现有很多很多，这里选几个经典的
 * 双重加锁 + volatile
 */
public class DoubleLock {
    public static void main(String[] args) {
        SingletonDemo singletonDemo = SingletonDemo.getInstance();
        System.out.println(singletonDemo);
    }

    static class SingletonDemo {
        private static volatile SingletonDemo singletonDemo;

        private SingletonDemo() {
            System.out.println("初始化啦....");
        }

        public static SingletonDemo getInstance() {
            if (singletonDemo == null) {
                synchronized (SingletonDemo.class) {
                    if (singletonDemo == null) {
                        singletonDemo = new SingletonDemo();
                    }
                }
            }
            return singletonDemo;
        }
    }
}
