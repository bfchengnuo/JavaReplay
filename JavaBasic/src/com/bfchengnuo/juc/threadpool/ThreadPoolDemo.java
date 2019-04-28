package com.bfchengnuo.juc.threadpool;

import java.util.concurrent.*;

/**
 * Created by 冰封承諾Andy on 2019/4/23.
 *
 * 常用的线程池，核心类 ThreadPoolExecutor / Executors(不推荐，参考阿里规范)
 *
 * - newCachedThreadPool - 带有缓存的，也就是大小可变的，适合执行短期的异步小程序，负担较轻的服务器；
 * - newFixedThreadPool - 固定数量的线程池，适合执行长期任务，负担比较重的服务器；
 * - newSingleThreadExecutor - 单例的线程池，适合单任务保证顺序的，例如秒杀；
 * - newScheduledThreadPool  - 执行周期任务的线程池；
 * - newWorkStealingPool - Java8 新增，并行级别为处理器数量
 *
 * 提交任务的两种方式：
 *      1.execute  - 无返回值
 *      2.submit   - 返回一个 Future
 *
 * ThreadPoolExecutor 构造的七个参数：
 *      corePoolSize - 线程池中的常驻核心线程数
 *      maximumPoolSize - 线程池支持的最大线程数，大于等于 1
 *      keepAliveTime - 多余的空闲线程存活时间（超过 corePoolSize 并且达到 keepAliveTime 就会被回收）
 *      unit - keepAliveTime 的单位
 *      workQueue - 任务队列，提交但是还没有执行的任务放在这里
 *      threadFactory - 用于生成工作线程的工厂
 *      handler - 拒绝策略，当线程数要超过最大数时应该如何处理
 *
 * 当 corePoolSize 和 workQueue 满了的时候，就会创建新的线程到 maximumPoolSize，如果超出则根据 handler 指定的进行处理
 *
 * handler 拒绝策略分为四种：
 *      1.AbortPolicy - 默认，它将抛出 RejectedExecutionException 异常。
 *      2.DiscardPolicy - 默认情况下它将丢弃被拒绝的任务。
 *      3.DiscardOldestPolicy - 它放弃最旧的未处理请求，然后重试 execute；如果执行程序已关闭，则会丢弃该任务。
 *      4.CallerRunsPolicy - 它直接在 execute 方法的调用线程中(回退到提交任务的线程执行)运行被拒绝的任务；如果执行程序已关闭，则会丢弃该任务。
 *
 * maximumPoolSize 如何合理配置？
 *      CPU 密集型，没有阻塞，一直全速运行，一般设置为 核数+1
 *      IO 密集型，一般并不是一直在执行任务（有阻塞），一般为 核数*2 或者 核数/(1-阻塞系数)，阻塞系数在 0.8-0.9
 */
public class ThreadPoolDemo {
    public static void main(String[] args) {
        // ExecutorService executorService = Executors.newFixedThreadPool(3);
        ExecutorService executorService = new ThreadPoolExecutor(2, 6,
                1, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(5),
                Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());

        try {
            executorService.execute(()-> System.out.println("execute"));
            executorService.submit(()-> System.out.println("submit"));
        } finally {
            executorService.shutdown();
        }
    }
}
