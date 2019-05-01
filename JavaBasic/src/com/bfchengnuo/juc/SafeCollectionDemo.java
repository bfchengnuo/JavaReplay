package com.bfchengnuo.juc;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by 冰封承諾Andy on 2019/4/22.
 *
 * ArrayList  HashMap  HashSet 都是不安全的，多线程操作例如 add 就会发生 CME
 *
 * 将线程不安全的集合转为安全的：
 *      使用 synchronized 外部同步
 *      ArrayList 替换为 Vector 更好的替换为 CopyOnWriteArrayList（读的时候不会加锁）
 *      HashMap 替换为 ConcurrentHashMap
 *      使用 Collections.synchronizedXXX 进行转换（使用的是包装或者装饰模式）
 *
 *      还可以了解下 ConcurrentSkipListSet、ConcurrentSkipListMap；
 *      与 ConcurrentHashMap 最大的区别就是写不需要加锁，但是需要的空间会大，以空间换时间
 *
 * copyOnWriteArrayList 写时复制，实现线程安全，先 Copy 然后再加入新的值，最后刷新引用（同步操作）
 *
 * Q：使用 Collections.synchronizedXXX 也是相当于方法加锁，为什不直接用 Vector？
 *    使用 Vector 是直接在方法上加锁，锁对象就是 Vector 对象，这样容易形成死锁；
 *    而 synchronizedXXX 方法使用单独的 mutex 进行加锁监控，虽然性能上基本没差别，但是安全性高了很多
 *
 * J9+ 之后可以使用 List.of()  Set.of() 等一系列 of 工厂方法得到一个不可变的集合对象，并且是只读的；
 * J5+ 可以用 CopyOnWriteArrayList，它也是不可变的；
 * 关于只读对象，可以了解下 Immutable 相关的类，ImmutableCollections 推荐 J11
 *
 * 手动利用 ConcurrentHashMap 实现 ConcurrentHashSet，
 * see https://github.com/mercyblitz/tech-weekly/blob/master/2019.04.12%20%E3%80%8C%E5%B0%8F%E9%A9%AC%E5%93%A5%E6%8A%80%E6%9C%AF%E5%91%A8%E6%8A%A5%E3%80%8D-%20%E7%AC%AC%E4%BA%8C%E5%8D%81%E4%B8%89%E6%9C%9F%E3%80%8A%E9%9D%A2%E8%AF%95%E8%99%90%E6%88%91%E5%8D%83%E7%99%BE%E9%81%8D%EF%BC%8CJava%20%E5%B9%B6%E5%8F%91%E7%9C%9F%E8%AE%A8%E5%8E%8C%EF%BC%88%E7%BB%AD%EF%BC%89%E3%80%8B/java-concurrency/src/com/imooc/interview/questions/java/concurrency/collection/ConcurrentHashSetQuestion.java
 *
 * JUC 中的集合大部分是弱一致性，返回的迭代器是可以进行修改的，但是如果是传统实现则会快速失败
 */
public class SafeCollectionDemo {
    private static List list = new ArrayList();
    private static List copyOnWriteArrayList = new CopyOnWriteArrayList();

    private static Map map = new HashMap();
    // private static Map map = new ConcurrentHashMap();

    public static void main(String[] args) {
        collectionCME();


        Collections.synchronizedList(list);

        // Java 9 + of 工厂方法，返回 Immutable 对象
        // list = List.of(1, 2, 3, 4, 5);
        // Set<Integer> set = Set.of(1, 2, 3, 4, 5);
        // Map<Integer, String> map = Map.of(1, "A");

        // list = new CopyOnWriteArrayList<>(list);
        // set = new CopyOnWriteArraySet<>(set);
        // map = new ConcurrentHashMap<>(map);
    }

    /**
     * 关于集合的 CME 异常；
     * 如果是 Fail-Fast 设计，就会抛出 CME 异常，例如 ArrayList；
     * 如果是 Fail-Safe 设计，就可以在遍历时进行操作，例如 CopyOnWriteArrayList；
     */
    private static void collectionCME() {
        // Arrays.asList 返回的是只读的集合
        List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
        // 下面的形式不会出 CME
        // List<Integer> list = new CopyOnWriteArrayList<>(Arrays.asList(1, 2, 3, 4));

        try {
            for (Integer integer : list) {
                list.remove(integer);
            }
            System.out.println("done...");
        } catch (Exception e) {
            System.out.println("发生异常：" + e.toString());
        }
    }
}
