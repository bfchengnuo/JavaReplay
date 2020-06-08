package com.bfchengnuo.juc;

import java.util.LinkedList;

/**
 * 等待超时模式，等待/唤醒机制
 * 跟异步有点相似，join 消耗太大（wait 0）
 *
 * @author 冰封承諾Andy
 * @date 2020/6/5
 */
public class WaitForTimeoutDemo {
    private static LinkedList<Object> pool = new LinkedList<>();

    public static void main(String[] args) {

    }

    /**
     * 示例代码，参考《Java 并发编程的艺术》 P107
     * @param mills 等待时间
     * @return 返回
     */
    private static Object get(long mills) throws InterruptedException {
        synchronized (pool) {
            // 完全超时
            if (mills <= 0) {
                while (pool.isEmpty()) {
                    pool.wait();
                }
                return pool.removeFirst();
            } else {
                long future = System.currentTimeMillis() + mills;
                long remaining = mills;
                while (pool.isEmpty() && remaining > 0) {
                    // 如果超时唤醒的情况，会抛出 InterruptedException
                    pool.wait(remaining);
                    // 可能提前唤醒
                    remaining = future - System.currentTimeMillis();
                }
                Object result = null;
                if (!pool.isEmpty()) {
                    result = pool.removeFirst();
                }
                return result;
            }
        }
    }
}
