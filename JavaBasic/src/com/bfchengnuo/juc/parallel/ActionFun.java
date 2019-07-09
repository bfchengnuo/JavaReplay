package com.bfchengnuo.juc.parallel;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.LongStream;

/**
 * 用于测试 Java7 的 ForkJoin 框架启动类
 *
 * Java7 中新加入的分支/合并框架的目的是以递归方式将可以并行的任务拆分成更小的任务，
 * 然后将每个子任务的结果合并起来生成整体结果。
 * 它是 ExecutorService 接口的一个实现，它把子任务分配给线程池（称为 ForkJoinPool ）中的工作线程。
 *
 * 在实际应用中，这些任务差不多被平均分配到 ForkJoinPool 中的所有线程上。
 * 每个线程都为分配给它的任务保存一个双向链式队列，每完成一个任务，就会从队列头上取出下一个任务开始执行。
 *
 * 某个线程可能早早完成了分配给它的所有任务，也就是它的队列已经空了，而其他的线程还很忙。
 * 这时，这个线程并没有闲下来，而是随机选了一个别的线程，从队列的尾巴上“偷走”一个任务，可以称为工作窃取（work stealing）
 *
 * see： https://github.com/bfchengnuo/MyRecord/blob/master/%E7%AC%94%E8%AE%B0/Java/Java8%2B/Java8%E5%AE%9E%E6%88%98%E7%AC%94%E8%AE%B0%EF%BC%88%E4%B8%89%EF%BC%89.md#%E5%88%86%E6%94%AF%E5%90%88%E5%B9%B6%E6%A1%86%E6%9E%B6
 *
 * @author Created by 冰封承諾Andy on 2019/7/2.
 */
public class ActionFun {
    public static void main(String[] args) {
        System.out.println(forkJoinSum(100));

    }

    /**
     * 测试求和的示例
     *
     * 对一个任务调用 join 方法会阻塞调用方，直到该任务做出结果
     * 不应该在 RecursiveTask 内部使用 ForkJoinPool 的 invoke 方法。
     * 相反，你应该始终直接调用 compute 或 fork 方法，只有顺序代码才应该用 invoke 来启动并行计算。
     * 对子任务调用 fork 方法可以把它排进 ForkJoinPool
     *
     * @param n 从 1 到的边界
     * @return 总和
     */
    private static long forkJoinSum(long n) {
        long[] numbers = LongStream.rangeClosed(1, n).toArray();
        ForkJoinTask<Long> task = new ForkJoinSumCalculator(numbers);
        return new ForkJoinPool().invoke(task);
    }
}
