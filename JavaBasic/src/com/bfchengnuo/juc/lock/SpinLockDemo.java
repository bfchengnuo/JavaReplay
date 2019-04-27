package com.bfchengnuo.juc.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by 冰封承諾Andy on 2019/4/22.
 *
 * 一个自旋锁的简单实现，自旋： 循环 + CAS
 * 适用于竞争不激烈、耗时短的情况，好处为不用阻塞；如果竞争激烈就会导致空转拖慢系统
 */
public class SpinLockDemo {
    static AtomicReference<Thread> threadAtomicReference = new AtomicReference<>();

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            lock();
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            unLock();
        }, "t1").start();

        // 保证 t1 先启动
        TimeUnit.SECONDS.sleep(1);

        new Thread(() -> {
            lock();
            unLock();
        }, "t2").start();
    }

    private static void lock() {
        while (!threadAtomicReference.compareAndSet(null, Thread.currentThread())) {
            // 自旋
        }
        System.out.println(Thread.currentThread().getName() + " come in...");
    }

    private static void unLock() {
        threadAtomicReference.compareAndSet(Thread.currentThread(), null);
        System.out.println(Thread.currentThread().getName() + " complete...");

    }
}
