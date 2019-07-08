package com.bfchengnuo.java8;

import java.util.*;

/**
 * J8 中在一些方面新增的一些 API
 * 例如在 集合、并发、数组等方面进行了追加，深层次的还有 JVM 方面的改变
 * String 和 Number 也有小部分更新
 *
 * see： https://github.com/bfchengnuo/MyRecord/blob/master/%E7%AC%94%E8%AE%B0/Java/Java8%2B/Java8%E5%AE%9E%E6%88%98%E7%AC%94%E8%AE%B0-%E9%99%84%E5%BD%95.md
 *
 * @author Created by 冰封承諾Andy on 2019/7/5.
 */
public class NewAppendApiDemo {
    public static void main(String[] args) {
        collectionExample();
        arraysExample();

        String authors = String.join(", ", "Raoul", "Mario", "Alan");
        System.out.println(authors);
    }

    /**
     * Java集合框架（Java Collections Framework）在 1.6 加入了 NavigableMap 和 NavigableSet 接口。
     * 分别的扩展了 SortedMap 和 SortedSet 接口，本质上添加了搜索选项到接口。
     *
     * NavigableMap: ConcurrentSkipListMap, TreeMap
     * NavigableSet: ConcurrentSkipListSet, TreeSet
     */
    private static void collectionExample() {
        Map<String, String> map = new HashMap<>(16);
        // 如果键被显式地映射到了空值，那么该方法是不会返回你设定的默认值的
        map.getOrDefault("k", "default");


        // 更方便的使用缓存模式
        Map<String, String> catchMap = new HashMap<>(16);
        // 旧方案
        String data = catchMap.get("key");
        if(data == null){
            data = "data";
            catchMap.put("key", data);
        }
        // 新方案
        catchMap.computeIfAbsent("key1", (key) -> key.concat("=val"));


        // 返回不可修改的、同步的、受检查的或者是空的 NavigableMap 或 NavigableSet
        NavigableMap<Object, Object> navigableMap = Collections.emptyNavigableMap();
        // UnmodifiableCollection系列, 非 1.8，效率不怎么好，推荐 Guava
        Collections.unmodifiableCollection(new ArrayList<>());
    }

    /**
     * 数组工具类新增方法
     */
    private static void arraysExample() {
        int[] evenNumbers = new int[10];
        Arrays.setAll(evenNumbers, i -> i * 2);
        System.out.println(Arrays.toString(evenNumbers));
        // out: [0, 2, 4, 6, 8, 10, 12, 14, 16, 18]

        // 并发计算
        int[] ones = new int[10];
        Arrays.fill(ones, 1);
        Arrays.parallelPrefix(ones, (a, b) -> a + b);
        System.out.println(Arrays.toString(ones));
        // out：[1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
    }
}
