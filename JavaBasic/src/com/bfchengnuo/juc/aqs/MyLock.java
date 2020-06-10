package com.bfchengnuo.juc.aqs;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * 自定义的一种独占锁，不支持重入
 * AQS （AbstractQueuedSynchronizer）是锁或者说其他任何同步组件的基础，
 * 它使用一个 int 变量来表示同步状态，通过内置 FIFO 队列来完成资源获取线程的排队工作;
 *
 * AQS 的主要使用方式是继承，然后实现抽象方法来管理同步状态，它也仅仅是提供了一些修改状态的方法，并且保证它们是安全的，有很大的灵活性。
 *
 * AQS 采用模版方法实现，使用者继承并重写相关方法，然后组合在自己的同步组件中进行使用。
 *
 * 阻塞与唤醒线程使用的是 {@link java.util.concurrent.locks.LockSupport} 工具类。
 * 需要等待唤醒机制就需要从 lock 对象中获取 {@link Condition} 对象，类似 Object 的 wait 等方法。
 *
 * @author 冰封承諾Andy
 * @date 2020/6/6
 */
public class MyLock implements Lock {
    /**
     * 内部使用 AQS 来管理状态相关
     */
    private static class Sync extends AbstractQueuedSynchronizer{
        /**
         * 是否处于占用状态, 只有用到 Condition 才需要去实现它
         * @return 1 表示占用中
         */
        @Override
        protected boolean isHeldExclusively() {
            return getState() == 1;
        }

        /**
         * 获取锁（独占锁）的逻辑
         * 当状态为 0 时尝试获取锁，被 AQS 的 acquire 方法回调；
         * @param arg 当前例子不需要，一般指获取锁的次数
         * @return 是否成功
         */
        @Override
        protected boolean tryAcquire(int arg) {
            // 使用 CAS 更新状态
            if (compareAndSetState(0, 1)) {
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }

        /**
         * 释放锁，重置为 0
         * @param arg 本例不需要，一般指释放锁的次数
         * @return 是否释放成功
         */
        @Override
        protected boolean tryRelease(int arg) {
            if (getState() == 0) {
                throw new IllegalMonitorStateException();
            }
            setExclusiveOwnerThread(null);
            setState(0);
            return true;
        }

        /**
         * 返回一个 Condition
         * @return 每一个 Condition 都包含一个 Condition 队列
         */
        private Condition newCondition() {
            return new ConditionObject();
        }
    }

    private final Sync sync = new Sync();
    @Override
    public void lock() {
        sync.acquire(1);
    }

    /**
     * 如果当前线程未被中断，则获取锁。
     * 如果该锁没有被另一个线程保持，则获取该锁并立即返回，将锁的保持计数设置为 1。
     * 如果当前线程已经保持此锁，则将保持计数加 1，并且该方法立即返回。
     * @throws InterruptedException 当前线程已经中断
     */
    @Override
    public void lockInterruptibly() throws InterruptedException {
        sync.acquireInterruptibly(1);
    }

    @Override
    public boolean tryLock() {
        return sync.tryAcquire(1);
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return sync.tryAcquireNanos(1, unit.toNanos(time));
    }

    @Override
    public void unlock() {
        sync.release(1);
    }

    @Override
    public Condition newCondition() {
        return sync.newCondition();
    }

    public boolean isLocked() {
        return sync.isHeldExclusively();
    }

    public boolean hasQueuedThreads() {
        return sync.hasQueuedThreads();
    }
}
