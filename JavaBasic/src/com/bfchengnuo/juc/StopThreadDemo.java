package com.bfchengnuo.juc;

import java.util.concurrent.TimeUnit;

/**
 * 停止线程的方法
 * 主要为标志法，不过因为 Java 的内存模型，需要使用 volatile 保证可见性；
 * 或者使用 synchronized 同步读写方法，注意，读写都需要进行同步
 *
 * @author 冰封承諾Andy
 * @date 2020/4/22
 */
public class StopThreadDemo {
    private static volatile Boolean stopRequested;

    public static void main(String[] args) throws InterruptedException {
        Thread backgroundThread = new Thread(() -> {
            int i = 0;
            while (!stopRequested) {
                i++;
            }
        });
        backgroundThread.start();
        TimeUnit.SECONDS.sleep(1);
        stopRequested = true;
    }

    private static synchronized void requestStop() {
        stopRequested = true;
    }

    private static synchronized Boolean stopRequested() {
        return stopRequested;
    }
}
