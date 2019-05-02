package com.bfchengnuo.juc.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by 冰封承諾Andy on 2019/4/23.
 * 生产者消费者模型的简单示例
 */
public class ProducerConsumerExample {
    public static void main(String[] args) throws InterruptedException {
        // classical();
        newImplement();
    }

    private static void newImplement() throws InterruptedException {
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(6);
        DataPlus dataPlus = new DataPlus(blockingQueue);

        new Thread(()->{
            try {
                dataPlus.producer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(()->{
            try {
                dataPlus.consumer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        TimeUnit.SECONDS.sleep(5);
        dataPlus.stop();
    }

    private static void classical() {
        Data data = new Data();

        new Thread(()->{
            for (int i = 0; i < 5; i++) {
                data.increase();
            }
        }).start();

        new Thread(()->{
            for (int i = 0; i < 5; i++) {
                data.decrease();
            }
        }).start();
    }

    /**
     * 使用阻塞队列实现
     */
    static class DataPlus{
        /**
         * 控制是否进行生产消费，默认开启
         */
        private volatile boolean flag = true;
        private AtomicInteger atomicInteger = new AtomicInteger();
        private BlockingQueue<String> blockingQueue;

        public DataPlus(BlockingQueue<String> blockingQueue) {
            this.blockingQueue = blockingQueue;
        }

        public void producer() throws InterruptedException {
            String temp;
            while (flag) {
                temp = String.valueOf(atomicInteger.getAndIncrement());
                boolean isOffer = blockingQueue.offer(temp, 2L, TimeUnit.SECONDS);
                if (isOffer) {
                    System.out.println("生产成功..." + temp);
                } else {
                    System.out.println("生产失败..." + temp);
                }

                TimeUnit.SECONDS.sleep(1L);
            }

            System.out.println("停止生产");
        }

        public void consumer() throws InterruptedException {
            String result;
            while (flag) {
                result = blockingQueue.poll(2, TimeUnit.SECONDS);
                if (result == null) {
                    flag = false;
                    System.out.println("超过 2s 未能获取到，启动退出机制");
                    return;
                }

                System.out.println("消费成功..." + result);
            }

            System.out.println("停止消费");
        }

        public void stop() {
            this.flag = false;
        }
    }

    /**
     * 一种手动简单的实现，使用 JDK 提供的 Lock
     */
    static class Data{
        private int num;
        private Lock lock = new ReentrantLock();
        private Condition condition = lock.newCondition();

        public void increase() {
            lock.lock();
            try {
                // 防止虚假唤醒，要使用 while
                while (num != 0) {
                    // 等待
                    condition.await();
                }
                num++;
                System.out.println("increase " + num);

                // 唤醒
                condition.signalAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

        public void decrease() {
            lock.lock();
            try {
                while (num == 0) {
                    // 等待
                    condition.await();
                }
                num--;
                System.out.println("decrease " + num);

                // 唤醒
                condition.signalAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }
}
