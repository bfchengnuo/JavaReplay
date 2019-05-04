package com.bfchengnuo.juc;

import com.sun.management.ThreadMXBean;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.util.concurrent.TimeUnit;

/**
 * Created by 冰封承諾Andy on 2019/4/28.
 *
 * 关于线程中断, 主要方法： interrupt 、interrupted 、isInterrupted
 *
 * 一个线程在未正常结束之前, 被强制终止是很危险的事情. 因为它可能带来完全预料不到的严重后果；
 * 比如会带着自己所持有的锁而永远的休眠，迟迟不归还锁等。
 *
 * 所以你看到 Thread.suspend, Thread.stop 等方法都被标记为 Deprecated 了。
 *
 * 那么不能直接把一个线程弄死, 但有时候又有必要让一个线程死掉, 或者让它结束某种等待的状态 该怎么办呢？
 * 一个比较优雅而安全的做法是: 使用等待/通知机制或者给那个线程一个中断信号, 让它自己决定该怎么办。
 *
 * 简单说就是，我们调用线程对象的 interrupt 方法改变线程的中断状态，这个线程自己会感知到，无论是阻塞状态还是非阻塞状态，
 *      就可以根据状态做出一定的反应；对于一些阻塞性方法例如 sleep、wait 当感知中断状态变化时，可能会抛出 InterruptedException
 *
 * interrupt 中断的是线程的某一部分业务逻辑，前提是线程需要检查自己的中断状态(isInterrupted())
 *
 * isInterrupted 和 interrupted 的区别，最大的就是一个不会重置中断状态，一个会重置状态，也就是说
 * interrupted 第一次获取如果是 true，那么第二次获取就是 false
 *
 * see https://github.com/bfchengnuo/MyRecord/issues/34  线程中的中断
 * see https://github.com/bfchengnuo/MyRecord/issues/35  线程中的状态
 *
 * Thread 对象只是一层包装，一个 Thread 对应 OS 的一个 Thread，由 JVM 进行管理和回收；
 * 当线程执行完毕或者出现异常（退出线程的两种方式），OS 层面的 Thread 就被回收了，但是 Java 层面还存在 Thread 对象；
 * 只不过这时候再调用 isAlive 方法返回的是 false，也就是只剩下包装了（无法重新启动）
 * 同时可以使用线程的 setUncaughtExceptionHandler 方法对异常进行处理，避免堆栈打印带来的性能损耗;
 * 或者使用 Thread 的静态方法 setDefaultUncaughtExceptionHandler 设置默认处理
 *
 * PS： 创建线程的方式有且仅有一种，就是 new Thread 的方式，其他的 callable、ThreadPool 都只能算线程的运行方式
 */
public class ThreadInterruptTest {
    private static volatile boolean stop = false;

    public static void main(String[] args) throws InterruptedException {
        // nonBlocking();
        // blockingTest();
        // reStart();
        getAllThreadStack();
        getAllThreadInfo();
        shutdownHook();
        threadGroup();
    }

    /**
     * 阻塞情况下使用线程中断
     */
    private static void blockingTest() throws InterruptedException {
        Thread example = new Thread(()->{
            while (!stop) {
                System.out.println("Thread running...");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    // 接收到一个中断异常（InterruptedException），从而提早地终结被阻塞状态
                    System.out.println("Thread interrupted...");
                }
            }

            System.out.println("Thread exiting under request...");
        });
        example.start();
        Thread.sleep(3000);

        System.out.println("Asking thread to stop...");
        stop = true;
        example.interrupt();

        Thread.sleep(3000);
        System.out.println("Stopping application...");
        System.exit(0);

        /*
        example.isAlive();
        example.setUncaughtExceptionHandler((thread, throwable) -> {

        });

        Thread.setDefaultUncaughtExceptionHandler();*/
    }


    /**
     * 非阻塞中使用线程中断的情况
     *
     * 线程并不会因为中断信号而停止运行。因为它只是被修改一个中断信号而已
     * 可以使用 isInterrupted 检测
     * @throws InterruptedException
     */
    private static void nonBlocking() throws InterruptedException {
        Thread t = new Thread(() -> {
            while (true) {
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("Someone interrupted me.");
                } else {
                    System.out.println("Thread is Going...");
                }
            }
        });
        t.start();
        Thread.sleep(3000);
        // 对非阻塞中的线程中断
        t.interrupt();
    }

    private static void reStart() throws InterruptedException {
        Thread t = new Thread(()-> System.out.println("线程执行..."));
        t.start();

        TimeUnit.SECONDS.sleep(1);

        System.out.println(t.isAlive());
        // 无法重新启动
        t.start();
    }

    /**
     * 获取 JVM 全部的线程信息的消费情况
     */
    private static void getAllThreadInfo() {
        ThreadMXBean threadMXBean = (ThreadMXBean) ManagementFactory.getThreadMXBean();
        long[] threadIds = threadMXBean.getAllThreadIds();

        for (long threadId : threadIds) {
            long bytes = threadMXBean.getThreadAllocatedBytes(threadId);
            long kBytes = bytes / 1024;
            System.out.printf("线程[ID:%d] 分配内存： %s KB\n", threadId, kBytes);
        }
    }

    /**
     * 获取 JVM 全部的线程栈信息
     */
    private static void getAllThreadStack() {
        java.lang.management.ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        long[] threadIds = threadMXBean.getAllThreadIds();

        for (long threadId : threadIds) {
            ThreadInfo threadInfo = threadMXBean.getThreadInfo(threadId);
            System.out.println(threadInfo.toString());
        }
    }

    /**
     * 当线程关闭时执行的钩子
     * Spring 中进行了大量使用，关闭 IoC 容器的时候
     */
    private static void shutdownHook() {
        Runtime runtime = Runtime.getRuntime();
        runtime.addShutdownHook(new Thread(()-> System.out.println("shutdownHook run..."), "shutdownHook"));
    }

    /**
     * 使用线程组来获得创建的线程
     */
    private static void threadGroup() {
        // main 线程 -> 子线程
        Thread t1 = new Thread(ThreadInterruptTest::action, "t1");
        Thread t2 = new Thread(ThreadInterruptTest::action, "t2");
        Thread t3 = new Thread(ThreadInterruptTest::action, "t3");

        // 不确定 t1、t2、t3 是否调用 start()
        t1.start();
        t2.start();
        t3.start();

        // 创建了 N Thread

        Thread mainThread = Thread.currentThread();
        // 获取 main 线程组
        ThreadGroup threadGroup = mainThread.getThreadGroup();
        // 活跃的线程数
        int count = threadGroup.activeCount();
        Thread[] threads = new Thread[count];
        // 把所有的线程复制 threads 数组
        threadGroup.enumerate(threads, true);

        for (Thread thread : threads) {
            System.out.printf("当前活跃线程: %s\n", thread.getName());
        }
    }

    private static void action() {
        System.out.printf("线程[%s] 正在执行...\n", Thread.currentThread().getName());
    }
}
