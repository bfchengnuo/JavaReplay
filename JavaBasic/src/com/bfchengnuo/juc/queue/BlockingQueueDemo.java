package com.bfchengnuo.juc.queue;

import java.util.concurrent.*;

/**
 * Created by 冰封承諾Andy on 2019/4/23.
 *
 * 阻塞队列，生产者消费者模型经常使用
 *
 * 队列为空时，获取操作会被阻塞；
 * 队列是满时，添加操作会被阻塞。
 *
 * API 支持： notify()  notifyAll()  wait()
 *
 * 实现：
 *      **ArrayBlockingQueue, 由数组结构组成的有界阻塞队列
 *      **LinkedBlockingQueue,  由链表结构组成的有界阻塞队列（默认大小： Integer.MAX_VALUE）
 *      **SynchronousQueue  不存储元素的阻塞队列，即单个元素的队列（生产一个，消费一个）
 *      DelayQueue,  使用优先级队列实现的无界延迟阻塞队列
 *      LinkedBlockingDeque,  由链表组成的双向阻塞队列
 *      LinkedTransferQueue,  由链表组成的无界阻塞队列（1.7+ 提供，相比之下性能会好一些）
 *      PriorityBlockingQueue,  支持优先级排序的无界阻塞队列（put 方法不阻塞（offer 不限制自动扩容），默认自然顺序排列）
 *
 * 常用方法：
 *       抛出异常       特殊值      阻塞          超时
 * 插入   add(e)      offer(e)    put(e)      offer(e, time, unit)
 * 移除   remove()    poll()      take()      poll(time, unit)
 * 检查   element()   peek()      不可用        不可用
 *
 * 特殊值指的是，当成功时返回 true，失败返回 false，当取不到元素时返回 null
 */
public class BlockingQueueDemo {
    public static void main(String[] args) throws Exception {
        // arrayBlockingQueueTest();
        // synchronousQueueTest();
        contrastTest();
    }

    private static void contrastTest() throws Exception {
        offer(new ArrayBlockingQueue<>(2));
        offer(new LinkedBlockingQueue<>(2));
        offer(new PriorityBlockingQueue<>(2));
        // take 会被阻塞
        offer(new SynchronousQueue<>());
    }

    private static void synchronousQueueTest() {
        SynchronousQueue<Integer> synchronousQueue = new SynchronousQueue<>();

        new Thread(()->{
            try {
                synchronousQueue.put(1);
                System.out.println("put 1");

                synchronousQueue.put(2);
                System.out.println("put 2");

                synchronousQueue.put(3);
                System.out.println("put 3");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(()->{
            try {
                // poll：如果没有元素可用，则返回 null
                // take：阻塞，被打断会抛出异常
                System.out.println("take " + synchronousQueue.take());
                TimeUnit.SECONDS.sleep(2);
                System.out.println("take " + synchronousQueue.take());
                TimeUnit.SECONDS.sleep(2);
                System.out.println("take " + synchronousQueue.take());
                System.out.println("take " + synchronousQueue.poll());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }


    private static void arrayBlockingQueueTest() throws InterruptedException {
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(4);

        System.out.println(blockingQueue.offer("a"));
        System.out.println(blockingQueue.offer("b"));
        System.out.println(blockingQueue.offer("c"));
        System.out.println(blockingQueue.offer("d"));
        System.out.println(blockingQueue.offer("e"));

        System.out.println(blockingQueue.offer("d", 2L, TimeUnit.SECONDS));

        System.out.println(blockingQueue.peek());

        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
    }

    private static void offer(BlockingQueue<Integer> queue) throws Exception {
        System.out.println("queue.getClass() = " + queue.getClass().getName());
        System.out.println("queue.offer(1) = " + queue.offer(1));
        System.out.println("queue.offer(2) = " + queue.offer(2));
        System.out.println("queue.offer(3) = " + queue.offer(3));
        System.out.println("queue.size() = " + queue.size());
        System.out.println("queue.take() = " + queue.take());
    }
}
