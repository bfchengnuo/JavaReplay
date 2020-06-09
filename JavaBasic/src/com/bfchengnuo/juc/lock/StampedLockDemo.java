package com.bfchengnuo.juc.lock;

import java.util.concurrent.locks.StampedLock;

/**
 * 继读写锁之后，为进一步提升并发执行效率，Java 8 引入了新的读写锁：StampedLock。
 * StampedLock 和 ReadWriteLock 相比，改进之处在于：读的过程中也允许获取写锁后写入！
 * 这样一来，我们读的数据就可能不一致，所以，需要一点额外的代码来判断读的过程中是否有写入，这种读锁是一种乐观锁(CAS)。
 *
 * StampedLock 是不可重入锁，跟读写锁一样，适用于写入竞争不高的情况；
 * 也可进行升级操作。
 *
 * @author 冰封承諾Andy
 * @date 2020/6/6
 */
public class StampedLockDemo {
    private final static StampedLock STAMPED_LOCK = new StampedLock();
    private static int x;
    private static int y;

    public static void main(String[] args) {
        // 获得一个乐观锁，返回版本号
        long stamp = STAMPED_LOCK.tryOptimisticRead();

        // 下面代码非原子，存在并发问题
        int currentX = x;
        int currentY = y;

        // 检查乐观读锁后是否有其他写锁获取，如果没有则上面都读是安全的
        if (!STAMPED_LOCK.validate(stamp)) {
            // 获取一个悲观读锁
            stamp = STAMPED_LOCK.readLock();
            try {
                currentX = x;
                currentY = y;
            } finally {
                // 释放悲观读锁
                STAMPED_LOCK.unlockRead(stamp);
            }
        }

        System.out.println(currentX + " : " + currentY);
    }

    private static void add(int i) {
        // 获取写锁, 返回一个版本
        long stamp = STAMPED_LOCK.writeLock();
        x += i;
        y += i;
        STAMPED_LOCK.unlock(stamp);
    }
}
