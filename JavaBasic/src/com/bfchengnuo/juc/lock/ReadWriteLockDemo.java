package com.bfchengnuo.juc.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by 冰封承諾Andy on 2019/4/22.
 * <p>
 * 简单的读写锁使用；
 * - 有读锁情况下可以读
 * - 有读锁情况下不可写
 * - 有写锁情况下不可写、读
 * <p>
 * 特性：
 * - 公平性选择
 * - 重进入
 * - 锁降级
 */
public class ReadWriteLockDemo {
    public static void main(String[] args) {
        MyCache myCache = new MyCache();

        // 5 个线程写入
        for (int i = 0; i < 5; i++) {
            String temp = String.valueOf(i);
            new Thread(() -> myCache.putVal(temp, temp)).start();
        }

        // 5 个线程读取
        for (int i = 0; i < 5; i++) {
            String temp = String.valueOf(i);
            new Thread(() -> {
                Object val = myCache.getVal(temp);
                System.out.println(Thread.currentThread().getId() + " 读取完成 ---> " + val);
            }).start();
        }
    }

    static class MyCache {
        // private Map<String, Object> map = new ConcurrentHashMap<>();
        private volatile Map<String, Object> map = new HashMap<>();

        private ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
        private Lock r = readWriteLock.readLock();
        private Lock w = readWriteLock.writeLock();


        public Object getVal(String key) {
            r.lock();
            try {
                System.out.println(Thread.currentThread().getId() + " 开始读取...");
                try {
                    TimeUnit.MILLISECONDS.sleep(300L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return map.get(key);
            } finally {
                r.unlock();
            }
        }

        public void putVal(String key, Object val) {
            w.lock();
            try {
                System.out.println(Thread.currentThread().getId() + " 准备写入...");
                try {
                    TimeUnit.MILLISECONDS.sleep(300L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                map.put(key, val);
                System.out.println(Thread.currentThread().getId() + " 写入完成...");
            } finally {
                w.unlock();
            }
        }

        public void info() {
            // 相关信息保存到 ThreadLocal 中
            System.out.println("当前读锁获取的次数：" + readWriteLock.getReadLockCount());
            System.out.println("当前线程读锁获取的次数：" + readWriteLock.getReadHoldCount());
            System.out.println("写锁是否被获取：" + readWriteLock.isWriteLocked());
            System.out.println("当前写锁被获取的次数：" + readWriteLock.getWriteHoldCount());
        }
    }
}
