package com.bfchengnuo.juc.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by 冰封承諾Andy on 2019/4/22.
 *
 * 简单的读写锁使用；
 * - 有读锁情况下可以读
 * - 有读锁情况下不可写
 * - 有写锁情况下不可写、读
 */
public class ReadWriteLockDemo {
    public static void main(String[] args) {
        MyCache myCache = new MyCache();

        // 5 个线程写入
        for (int i = 0; i < 5; i++) {
            String temp = String.valueOf(i);
            new Thread(()->{
                myCache.putVal(temp, temp);
            }).start();
        }

        // 5 个线程读取
        for (int i = 0; i < 5; i++) {
            String temp = String.valueOf(i);
            new Thread(()->{
                Object val = myCache.getVal(temp);
                System.out.println(Thread.currentThread().getId() + " 读取完成 ---> " + val);
            }).start();
        }
    }

    static class MyCache{
        // private Map<String, Object> map = new ConcurrentHashMap<>();
        private volatile Map<String, Object> map = new HashMap<>();

        private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

        public Object getVal(String key) {
            readWriteLock.readLock().lock();
            try {
                System.out.println(Thread.currentThread().getId() + " 开始读取...");
                try {
                    TimeUnit.MILLISECONDS.sleep(300L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return map.get(key);
            } finally {
                readWriteLock.readLock().unlock();
            }
        }

        public void putVal(String key, Object val) {
            readWriteLock.writeLock().lock();
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
                readWriteLock.writeLock().unlock();
            }
        }
    }
}
