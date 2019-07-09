package com.bfchengnuo.juc.parallel;

/**
 * 求和示例
 * <p>
 * 要把任务提交到这个池，必须创建 RecursiveTask<R> 的一个子类，
 * 其中 R 是并行化任务（以及所有子任务）产生的结果类型，或者如果任务不返回结果，则是 RecursiveAction 类型。
 * <p>
 * 伪代码：
 * if (任务足够小或不可分) {
 *     顺序计算该任务
 * } else {
 *     将任务分成两个子任务
 *     递归调用本方法，拆分每个子任务，等待所有子任务完成
 *     合并每个子任务的结果
 * }
 *
 * @author Created by 冰封承諾Andy on 2019/7/2.
 */
class ForkJoinSumCalculator extends java.util.concurrent.RecursiveTask<Long> {
    /**
     * 任务不可再分的阀值
     */
    private static final long THRESHOLD = 10_000;
    /**
     * 要求和的数组
     */
    private final long[] numbers;
    /**
     * 子任务处理数组的开始与结束位置
     */
    private final int start;
    private final int end;

    ForkJoinSumCalculator(long[] numbers) {
        this(numbers, 0, numbers.length);
    }

    private ForkJoinSumCalculator(long[] numbers, int start, int end) {
        this.numbers = numbers;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        int length = end - start;
        if (length <= THRESHOLD) {
            return computeSequentially();
        }

        // 创建一个子任务来为数组的前一半求和
        ForkJoinSumCalculator leftTask =
                new ForkJoinSumCalculator(numbers, start, start + length / 2);
        // 利用另一个 ForkJoinPool 线程异步执行新创建的子任务
        leftTask.fork();

        ForkJoinSumCalculator rightTask =
                new ForkJoinSumCalculator(numbers, start + length / 2, end);
        // 同步执行第二个子任务，有可能允许进一步递归划分 (重用本线程)
        Long rightResult = rightTask.compute();

        // 读取第一个子任务的结果，如果尚未完成就等待
        Long leftResult = leftTask.join();
        // 合并操作
        return leftResult + rightResult;
    }

    private long computeSequentially() {
        long sum = 0;
        for (int i = start; i < end; i++) {
            sum += numbers[i];
        }
        return sum;
    }
}