package com.bfchengnuo.juc;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * Created by 冰封承諾Andy on 2019/4/22.
 *
 * Java  中除了偏向锁，基本都使用了 CAS，建议了解下底层的原理
 *
 * CAS 的缺点：
 *      1.循环时间长开销很大。
 *      2.只能保证一个共享变量的原子操作。
 *      3.ABA 问题。
 *
 * ABA 问题： 记录版本，对应的类
 * 一个共享变量？ 使用
 * 循环（自旋）开销大？ 使用对应的汇编指令，详见《Java并发编程的艺术》
 */
public class AtomSimpleDemo {
    /**
     * 带有版本的，解决 ABA 问题
     */
    private static AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<>(0, 1);

    private static AtomicReference<User> atomicReference = new AtomicReference<>(new User());

    private static AtomicInteger atomicInteger = new AtomicInteger(0);

    public static void main(String[] args) {
        // 自增
        System.out.println("atomicInteger:" + atomicInteger.getAndIncrement());
        // 比较并替换； 会托管给 unsafe 的 compareAndSwapInt  native 方法
        atomicInteger.compareAndSet(1, 10);
        // 以原子方式设置为给定值，并返回旧值。
        atomicInteger.getAndSet(20);
        atomicInteger.updateAndGet(x -> x * 2);
        System.out.println("atomicInteger:" + atomicInteger.get());

        System.out.println();

        System.out.println(atomicReference.get().toString());

        atomicStampedReference.compareAndSet(0, 100, 1, atomicStampedReference.getStamp() + 1);
        System.out.println("atomicStampedReference ref: " + atomicStampedReference.getReference());
        System.out.println("atomicStampedReference stamp: " + atomicStampedReference.getStamp());
    }

    static class User{
        private String name;
        private Integer age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }
    }
}
