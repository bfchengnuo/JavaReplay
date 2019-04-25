package com.bfchengnuo.juc;

import java.util.concurrent.CountDownLatch;

/**
 * Created by 冰封承諾Andy on 2019/4/22.
 * CountDownLatch 一个同步辅助类，在完成一组正在其他线程中执行的操作之前，它允许一个或多个线程一直等待。
 */
public class CountDownDemo {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(5);

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
}
