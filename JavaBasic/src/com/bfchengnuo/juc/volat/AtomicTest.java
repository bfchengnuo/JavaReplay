package com.bfchengnuo.juc.volat;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by 冰封承諾Andy on 2019/4/21.
 *
 * volatile 不能保证原子性的测试；
 * 准确来说 volatile 能保证部分的原子性，例如基本数据类型基本可以保证原子性（i++ 不是一条指令）
 *
 * volatile 的实现原理是内存屏障（锁）
 */
public class AtomicTest {
    private static volatile int num;
    private static volatile AtomicInteger atomNum = new AtomicInteger();

    /**
     * 如果有guava 可以使用这个标识线程名字
     * new ThreadFactoryBuilder().setNameFormat("thread-call-runner-%d").build();
     */
    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 10, 0L,
                TimeUnit.SECONDS, new LinkedBlockingQueue<>(), new ThreadPoolExecutor.AbortPolicy());
        // 10 个线程操作，每个线程 +100 次，如果是原子的，那么最终结果是 1000
        for (int i = 0; i < 10; i++) {
            threadPoolExecutor.execute(() -> {
                try {
                    TimeUnit.SECONDS.sleep(1L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                for (int j = 0; j < 100; j++) {
                    add();
                }
                System.out.println("线程 "+Thread.currentThread().getId()+" 处理完毕...");
            });
        }

        // Thread.activeCount() > 2 非线程池判断； GC and Main
        threadPoolExecutor.shutdown();
        while (!threadPoolExecutor.isTerminated()) {
            Thread.yield();
        }

        System.out.println("main....num=" + num);
        System.out.println("main....atomNum=" + atomNum);
    }

    private static /*synchronized*/ void add() {
        // 此语句不是原子的
        num++;
        atomNum.getAndIncrement();
    }
}
