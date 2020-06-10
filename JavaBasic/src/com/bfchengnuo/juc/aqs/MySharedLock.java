package com.bfchengnuo.juc.aqs;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * 自定义的一种共享式锁
 * 与 {@link MyLock} 基本一致，使用的是 xxxShared 方法。
 *
 * 通过 AQS 内部的状态标识来记录共享数实现。
 *
 * @author 冰封承諾Andy
 * @date 2020/6/7
 */
public class MySharedLock implements Lock {
    private static final class Sync extends AbstractQueuedSynchronizer {
        public Sync(int count) {
            if (count <= 0) {
                throw new IllegalArgumentException("共享数量错误");
            }
            setState(count);
        }

        /**
         * 尝试获取锁（共享式）
         * @param reduceCount 获取锁的数量
         * @return 返回大于 0 才是获取成功
         */
        @Override
        protected int tryAcquireShared(int reduceCount) {
            for (; ; ) {
                int current = getState();
                int newCount = current - reduceCount;
                if (newCount < 0 || compareAndSetState(current, newCount)) {
                    return newCount;
                }
            }
        }

        /**
         * 释放锁，与获取类似
         * @param returnCount 释放的数量
         * @return 是否成功
         */
        @Override
        protected boolean tryReleaseShared(int returnCount) {
            for (; ; ) {
                int current = getState();
                int newCount = current + returnCount;
                if (compareAndSetState(current, newCount)) {
                    return true;
                }
            }
        }
    }

    private final Sync sync = new Sync(2);

    @Override
    public void lock() {
        sync.acquireShared(1);
    }

    @Override
    public void unlock() {
        sync.releaseShared(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        sync.acquireSharedInterruptibly(1);
    }

    @Override
    public boolean tryLock() {
        return sync.tryAcquireShared(1) > 0;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return sync.tryAcquireSharedNanos(1, unit.toNanos(time));
    }

    @Override
    public Condition newCondition() {
        return null;
    }
}
