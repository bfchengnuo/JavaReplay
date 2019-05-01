package com.bfchengnuo.juc;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by 冰封承諾Andy on 2019/4/23.
 *
 * 一个同步辅助类，它允许一组线程互相等待，直到到达某个公共屏障点 (common barrier point)。在涉及一组固定大小的线程的程序中，这些线程必须不时地互相等待，此时 CyclicBarrier 很有用。因为该 barrier 在释放等待线程后可以重用，所以称它为循环 的 barrier。
 *
 * 简单说它与 CountDownLatch 相反，一个是减到一定的程度，一个是加到一定的程度
 *
 * 使用线程池执行时，要注意 CyclicBarrier 计数完成后不会自动重置，需要在全部执行完成前手动 reset (超时可能会抛出异常)
 */
public class CyclicBarrierDemo {
    private static CyclicBarrier cyclicBarrier = new CyclicBarrier(4, () -> System.out.println("全部到达临界点！"));

    public static void main(String[] args) {
        for (int i = 0; i < 4; i++) {
            new Thread(()->{
                try {
                    System.out.println(Thread.currentThread().getId() + " 进入...");

                    // 在所有参与者都已经在此 barrier 上调用 await 方法之前，将一直等待。
                    // 相当于先计数 -1 再判断 > 0
                    // cyclicBarrier.await() =  countDownLatch.countDown() + await()
                    cyclicBarrier.await();

                    System.out.println(Thread.currentThread().getId() + " 完成...");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();

            cyclicBarrier.reset();
        }
    }
}
