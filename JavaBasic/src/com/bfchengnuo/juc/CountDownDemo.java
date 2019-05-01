package com.bfchengnuo.juc;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by 冰封承諾Andy on 2019/4/22.
 * CountDownLatch 一个同步辅助类，在完成一组正在其他线程中执行的操作之前，它允许一个或多个线程一直等待。
 */
public class CountDownDemo {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(5);
        // MyCountDownLatch countDownLatch = new MyCountDownLatch(6);

        for (int i = 0; i < 5; i++) {
            String temp = String.valueOf(i + 1);
            new Thread(()->{
                System.out.printf("步骤 %s 完成...\n", temp);
                countDownLatch.countDown();
            }).start();
        }

        // countDownLatch 减为 0 时才会继续执行
        countDownLatch.await();
        System.out.println("最终操作");
    }

    /**
     * 简单实现 CountDownLatch
     * 简单理解也是消费者生产者的关系
     */
    static class MyCountDownLatch {
        private int count;
        private final Lock lock = new ReentrantLock();
        private final Condition condition = lock.newCondition();

        public MyCountDownLatch(int count) {
            this.count = count;
        }

        public void countDown() {
            lock.lock();
            try {
                if (count < 1) {
                    return;
                }
                count--;
                if (count < 1) {
                    // 唤醒
                    condition.signalAll();
                }
            } finally {
                lock.unlock();
            }
        }

        public void await() throws InterruptedException {
            // 测试当前线程是否已经中断, 并且会重置中断状态
            if (Thread.interrupted()) {
                throw new InterruptedException();
            }

            lock.lock();
            try {
                while (count > 0) {
                    // 阻塞
                    condition.await();
                }
            } finally {
                lock.unlock();
            }
        }
    }
}
