package com.bfchengnuo.juc.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by 冰封承諾Andy on 2019/4/22.
 * <p>
 * JUC 提供的可重入锁： ReentrantLock
 *
 *
 */
public class ReenterableDemo {
    /**
     * 构造方法接受一个布尔值，默认 false
     * true - 公平锁， false - 非公平锁
     * 公平： 按照顺序来获取锁； 非公平： 不按照申请顺序来获得锁
     * <p>
     * 等待队列， FIFO； 非公平锁先尝试获取锁，如果失败再排队
     * 非公平锁的吞吐量一般比较大；
     * synchronized 也是一种非公平锁；
     */
    static Lock reentrantLock = new ReentrantLock();

    public static void main(String[] args) {
        // lock 和 unlock 成对出现就好
        reentrantLock.lock();
        reentrantLock.lock();
        try {
            // do something
        } finally {
            reentrantLock.unlock();
            reentrantLock.unlock();
        }
    }
}
