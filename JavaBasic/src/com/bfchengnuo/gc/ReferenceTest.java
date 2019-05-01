package com.bfchengnuo.gc;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

/**
 * Created by 冰封承諾Andy on 2019/4/23.
 *
 * GC Root：
 *      虚拟机栈
 *      方法区的静态类属性
 *      方法区常量引用
 *      本地方法栈
 *
 * 1.强引用：就算 OOM 也不会回收，死也不收
 * 2.软引用：内存不够的时候会回收，SoftReference
 * 3.弱引用：不管够不够，都回收，WeakHashMap
 * 4.虚引用：形同虚设，任何时候都有可能被回收; 不能单独使用, 配合 ReferenceQueue，也不能通过它得到引用对象
 *          它的作用是跟踪对象被垃圾回收的状态，被回收后做某些事
 *
 * 强引用我们平常使用的最多，软、弱在缓存方面使用的比较多，例如 Mybatis 的缓存；
 *
 * 软、弱引用在回收时会先添加到 ReferenceQueue
 *
 * 在 ThreadLocal 中的 ThreadLocalMap 里的 Entry extends WeakReference<ThreadLocal> 所以它是一个弱引用
 */
public class ReferenceTest {
    public static void main(String[] args) throws InterruptedException {
        simpleTest();
        System.out.println("===================================");
        phantomReferenceTest();
    }

    private static void simpleTest() {
        SoftReference<String> stringSoftReference = new SoftReference<>("test");
        System.out.println(stringSoftReference.get());

        Map<Integer, String> hashMap = new HashMap<>(1);
        Map<Integer, String> weakHashMap = new WeakHashMap<>();

        // 避免命中缓存
        Integer key = 133;
        String val = "val";
        Integer key2 = 233;
        String val2 = "val2";

        hashMap.put(key, val);
        weakHashMap.put(key2, val2);

        key = null;
        key2 = null;
        System.gc();

        System.out.println("hashMap " + hashMap + " size:" + hashMap.size());
        System.out.println("weakHashMap " + weakHashMap + " size:" + weakHashMap.size());
    }

    private static void phantomReferenceTest() throws InterruptedException {
        ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();
        Object val = new Object();
        Object val2 = new Object();
        WeakReference<Object> weakReference = new WeakReference<>(val, referenceQueue);
        PhantomReference<Object> phantomReference = new PhantomReference<>(val2, referenceQueue);


        System.out.println("GC 前");
        System.out.println("val:" + val);
        System.out.println("val2:" + val2);
        System.out.println("weakReference: " + weakReference.get());
        System.out.println("phantomReference: " + phantomReference.get());
        System.out.println("referenceQueue: " + referenceQueue.poll());
        System.out.println("referenceQueueNext: " + referenceQueue.poll());

        val = null;
        val2 = null;
        System.gc();
        Thread.sleep(300);

        System.out.println("\nGC 后");
        System.out.println("val:" + val);
        System.out.println("val2:" + val2);
        System.out.println("weakReference:  " + weakReference.get());
        System.out.println("phantomReference:  " + phantomReference.get());
        System.out.println("referenceQueue:  " + referenceQueue.poll());
        System.out.println("referenceQueueNext:  " + referenceQueue.poll());
    }
}
