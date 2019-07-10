package com.bfchengnuo.juc.threadpool;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * 如何创建一个合适的线程池？
 * PS： Java 程序无法终止或者退出一个正在运行中的线程
 * <p>
 * 由 {@link com.bfchengnuo.java8.stream.AsyncDemo} 引发
 * 参考： https://github.com/bfchengnuo/MyRecord/blob/master/%E7%AC%94%E8%AE%B0/Java/Java8%2B/Java8%E5%AE%9E%E6%88%98%E7%AC%94%E8%AE%B0%EF%BC%88%E5%9B%9B%EF%BC%89%E7%BB%84%E5%90%88%E5%BC%8F%E5%BC%82%E6%AD%A5%E7%BC%96%E7%A8%8B.md#%E5%85%8D%E5%8F%97%E9%98%BB%E5%A1%9E
 *
 * @author Created by 冰封承諾Andy on 2019/7/4.
 */
public class CustomizeCreatePool {
    private int size;
    private final Executor executor = Executors.newFixedThreadPool(
            // 最小值 100
            Math.min(size, 100),
            // ThreadFactory
            r -> {
                Thread t = new Thread(r);
                // 设置为守护线程，不会阻止程序的关停
                t.setDaemon(true);
                return t;
            });

    public CustomizeCreatePool(int size) {
        this.size = size;
    }

    /**
     * 运行测试
     */
    public static void main(String[] args) {
        CustomizeCreatePool pool = new CustomizeCreatePool(20);
        CompletableFuture.supplyAsync(
                () -> "doSomething",
                pool.getExecutor());
    }

    public static Executor get() {
        // 创建线程池
        return Executors.newFixedThreadPool(
                // 最小值 100
                Math.min(20, 100),
                new ThreadFactory() {
                    @Override
                    public Thread newThread(Runnable r) {
                        Thread t = new Thread(r);
                        // 设置为守护线程，不会阻止程序的关停
                        t.setDaemon(true);
                        return t;
                    }
                });
    }

    public Executor getExecutor() {
        return executor;
    }
}
