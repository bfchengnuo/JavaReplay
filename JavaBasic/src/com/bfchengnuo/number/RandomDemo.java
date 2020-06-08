package com.bfchengnuo.number;

import java.util.concurrent.ThreadLocalRandom;

/**
 * 随机数生成示范，JDK7+ 推荐使用 ThreadLocalRandom
 * 传统 Random 的局限性：
 * Random 虽然是线程安全的，但是在多线程下效率比较低，而且 Random 产生的随机数可能被预测，顾不适合做验证码类的随机数生产。
 * 对于并发访问，使用 TheadLocalRandom 代替 Math.random() 可以减少竞争，从而获得更好的性能。
 *
 * ThreadLocalRandom 是一个与当前线程隔离的随机数生成器。它通过为每个线程实例化一个随机数生成器，来减少系统开销和对资源的争用。
 *
 * Random 虽然线程安全，但是存在多线程竞争的情况，使用 CAS 保证安全；
 * ThreadLocalRandom 每个线程独立，不存在竞争，也无需锁
 *
 * @author 冰封承諾Andy
 * @date 2020/4/23
 */
public class RandomDemo {
    public static void main(String[] args) {
        ThreadLocalRandom localRandom = ThreadLocalRandom.current();
        int random= localRandom.nextInt();
        System.out.println(random);

        // [10, 15) 区间
        System.out.println(localRandom.nextInt(10, 15));

        // JDK8+  正态分布
        System.out.println(localRandom.nextGaussian());

        localRandom.ints().limit(3).forEach(System.out::println);
    }
}
