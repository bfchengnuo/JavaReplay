package com.bfchengnuo.juc.threadpool;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Created by 冰封承諾Andy on 2019/4/23.
 * FutureTask 的简单使用;
 * 多个线程执行同一个 FutureTask，最终只会执行一次
 */
public class FutureDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<String> futureTask = new FutureTask<>(() -> {
            System.out.println("is Callable");
            return "HelloWorld";
        });
        // 使用 FutureTask 可以对 Callable 进行适配，用 Thread 执行
        Thread thread = new Thread(futureTask);
        Thread thread2 = new Thread(futureTask);

        thread.start();
        thread2.start();

        // 获取 Callable 的返回值
        System.out.println(futureTask.get());
        futureTask.isDone();
    }
}
