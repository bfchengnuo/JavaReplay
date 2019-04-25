package com.bfchengnuo.juc.volat;

import java.util.concurrent.TimeUnit;

/**
 * Created by 冰封承諾Andy on 2019/4/21.
 * <p>
 * volatile 保证可见性的测试(不保证原子性，可以禁止指令重排)
 * 可见性：
 * 由 Java 内存模型可知，线程操作变量会先从主存复制到工作内存，然后再操作，最后写回主存；
 * 可见性保证了当主存的变量被更新后，其他线程会收到通知，自动更新各自工作内存的变量值
 *
 * 由于不保证原子性，所以线程安全问题依然存在，虽然能保证每次拿到的是新值，但不具备“响应式”特点, 而操作完毕写回主存也不是原子指令
 *
 * 适用范围： 开关变量
 * - 对变量的写操作不依赖于当前值
 * - 该变量没有包含在具有其他变量的不变式中
 *
 * see https://www.ibm.com/developerworks/cn/java/j-jtp06197.html
 */
public class VisibilityTest {
    // private static int num;
    private static volatile int num;

    public static void main(String[] args) {
        new Thread(() -> {
            // 模拟耗时
            try {
                System.out.printf("%s 开始执行... num=%s \n", Thread.currentThread().getName(), num);
                TimeUnit.SECONDS.sleep(3L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            updateNum();
            System.out.printf("%s 执行完毕... num=%s \n", Thread.currentThread().getName(), num);
        }, "Loli").start();

        // 非 volatile 这里只会不停读取工作内存的值，感知不到主存的值变化，所以会卡死在这
        while (num == 0) {}

        System.out.println("main complete, num=" + num);
    }

    private static void updateNum() {
        num = 10;
    }
}
