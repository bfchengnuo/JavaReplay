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
 *      ArrayList 替换为 Vector 更好的替换为 CopyOnWriteArrayList
 *      HashMap 替换为 ConcurrentHashMap
 *      使用 Collections.synchronizedXXX 进行转换
 *
 * copyOnWriteArrayList 写时复制，实现线程安全，先 Copy 然后再加入新的值，最后刷新引用（同步操作）
 */
public class SafeCollectionDemo {
    private static List list = new ArrayList();
    private static List copyOnWriteArrayList = new CopyOnWriteArrayList();

    private static Map map = new HashMap();
    // private static Map map = new ConcurrentHashMap();

    public static void main(String[] args) {
        Collections.synchronizedList(list);


    }
}
