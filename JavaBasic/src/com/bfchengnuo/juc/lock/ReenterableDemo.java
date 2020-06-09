package com.bfchengnuo.juc.lock;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by 冰封承諾Andy on 2019/4/22.
 * <p>
 * JUC 提供的可重入锁： ReentrantLock
 *
 * synchronized 可以配合 wait 和 notify 实现线程在条件不满足时等待，条件满足时唤醒;
 * ReentrantLock 可以使用 Condition 对象来实现 wait 和 notify 的功能;
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
    private final static Lock reentrantLock = new ReentrantLock();
    private static final Condition condition = reentrantLock.newCondition();
    private static Queue<String> queue = new LinkedList<>();

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

    /**
     * Condition 提供的 await()、signal()、signalAll() 原理和 synchronized 锁对象的 wait()、notify()、notifyAll() 是一致的，
     * 并且其行为也是一样的.
     * if (condition.await(1, TimeUnit.SECOND)) {
     *     // 被其他线程唤醒
     * } else {
     *     // 指定时间内没有被其他线程唤醒
     * }
     */
    public static String getTask() throws InterruptedException {
        reentrantLock.lock();
        try {
            while (queue.isEmpty()) {
                condition.await();
            }
            return queue.remove();
        } finally {
            reentrantLock.unlock();
        }
    }

    public static void addTask(String s) {
        reentrantLock.lock();
        try {
            queue.add(s);
            condition.signalAll();
        } finally {
            reentrantLock.unlock();
        }
    }
}
