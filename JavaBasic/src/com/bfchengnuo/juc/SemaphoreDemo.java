package com.bfchengnuo.juc;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * Created by 冰封承諾Andy on 2019/4/23.
 *
 * 一个计数信号量，信号量维护了一个许可集允许指定数量的执行，其他被阻塞
 * 适用于做流控。
 */
public class SemaphoreDemo {
    /**
     * 第二个参数为是否为公平锁
     */
    private static Semaphore semaphore = new Semaphore(4, false);

    public static void main(String[] args) {
        int max = 3;
        int min = 1;
        Random random = new Random();

        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                try {
                    // 从此信号量获取一个许可，在提供一个许可前一直将线程阻塞，否则线程被中断。可以获取多个 (int)
                    semaphore.acquire();


                    long sleepTime = random.nextInt(max-min) + min;
                    System.out.println(Thread.currentThread().getId() + " 获取成功; " + sleepTime);
                    TimeUnit.SECONDS.sleep(sleepTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    // 释放一个许可，将其返回给信号量。 可以释放多个
                    semaphore.release();
                    System.out.println(Thread.currentThread().getId() + " 释放成功");
                }
            }).start();
        }
    }
}
