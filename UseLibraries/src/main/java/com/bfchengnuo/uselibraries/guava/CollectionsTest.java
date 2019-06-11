package com.bfchengnuo.uselibraries.guava;

import com.google.common.collect.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 关于集合相关的工具类演示以及集合增强
 *
 * @author Created by 冰封承諾Andy on 2019/6/9.
 */
public class CollectionsTest {

    /**
     * 不可变集合
     * <p>
     * JDK 也提供了 Collections.unmodifiableXXX 方法把集合包装为不可变形式，但我们认为不够好
     * <p>
     * 所有 Guava 不可变集合的实现都不接受 null 值。
     */
    public static void immutableCollectionTest() {
        // 1. 使用 of 创建
        final ImmutableSet<String> COLOR_NAMES = ImmutableSet.of(
                "red",
                "orange",
                "yellow",
                "green",
                "blue",
                "purple");
        System.out.println(COLOR_NAMES);

        // 2. 使用 copyOf 方法，转换集合
        //      copyOf 方法会尝试在安全的时候避免做拷贝——通常来说是很智能的;
        //      例如你传入的本身就是一个 Immutable 集合，那么就不会拷贝直接调用 asList 之类的方法
        List<String> stringList = Lists.newArrayList();
        stringList.add("Test");
        ImmutableList<String> immutableList = ImmutableList.copyOf(stringList);
        System.out.println(immutableList);

        // 3. 使用 Builder 工具
        final ImmutableSet<String> GOOGLE_COLORS =
                ImmutableSet.<String>builder()
                        .addAll(COLOR_NAMES)
                        .add("New")
                        .build();
        System.out.println(GOOGLE_COLORS);

        // 对有序不可变集合来说，排序是在构造集合的时候完成的
        ImmutableSortedSet<String> sortedSet = ImmutableSortedSet.of("a", "b", "c", "a", "d", "b");
        System.out.println(sortedSet);
    }

    /**
     * 新集合 Multiset
     * <p>
     * 它可以多次添加相等的元素。Multiset 元素的顺序是无关紧要的：Multiset {a, a, b}和{a, b, a}是相等的”。
     * <p>
     * Multiset继承自 JDK 中的 Collection 接口，而不是 Set 接口
     * <p>
     * 可以看作： 1.没有顺序限制的 ArrayList； 2.键为元素，值为计数的 Map
     * 注意 Multiset 不是 Map！
     */
    public static void multisetTest() {
        HashMultiset<String> multiSet = HashMultiset.create();

        for (int i = 0; i < 5; i++) {
            multiSet.add("test", i);
        }
        // 0 + 1 + 2 + 3 + 4 = 10
        System.out.println(multiSet.count("test"));
        // 清空
        multiSet.setCount("test", 0);
        System.out.println(multiSet.count("test"));


        System.out.println();
        Object object = new Object();
        HashMultiset<Object> multiSet2 = HashMultiset.create();

        // 如果存在，加或者减去数量
        if (multiSet2.contains(object)) {
            multiSet2.add(object, 12);
            // multiSet2.remove(object, 10);
        } else {
            // 如果不存在，设置初始值
            multiSet2.add(object, 1);
        }

        // 输出某个对象（key）最终所对应的数值
        System.out.println(multiSet2.count(object));
    }


    /**
     * 新集合 Multimap
     * <p>
     * Multimap 可以很容易地把一个键映射到多个值。换句话说，Multimap 是把键映射到任意多个值的一般方式。
     * 简单可理解为 Map<K, List> 或 Map<K, Set> 这种结构，但是并不一样，其中的 asMap() 方法返回 Map<K, Collection> 视图可以这样认为。
     * <p>
     * Multimap 认为映射是 a -> 1， a -> 2， a ->4  b -> 3， c -> 5  没毛病
     * <p>
     * 不会有任何键映射到空集合：一个键要么至少到一个值，要么根本就不在 Multimap 中。
     */
    public static void multimapTest() {
        ListMultimap<String, String> myMultimap = ArrayListMultimap.create();
        myMultimap.put("Fruits", "Banana");
        myMultimap.put("Fruits", "Apple");
        myMultimap.put("Fruits", "Pear");
        myMultimap.put("Fruits", "Pear");
        myMultimap.put("Vegetables", "Carrot");

        // entries() 保留了所有键和值的迭代顺序
        myMultimap.entries().forEach(entry -> {
            System.out.println(entry.getKey() + "::" + entry.getValue());
        });
    }

    /**
     * 新集合 BiMap
     * <p>
     * 用于双向映射的场景
     * 可以用 inverse() 反转 BiMap<K, V> 的键值映射
     *
     * 如果你想把键映射到已经存在的值，会抛出 IllegalArgumentException 异常。如果对特定值，你想要强制替换它的键，请使用 BiMap.forcePut(key, value)。
     */
    public static void biMapTest() {
        BiMap<Integer,String> biMap = HashBiMap.create();
        biMap.put(1,"a");
        biMap.put(2,"b");
        biMap.put(3,"c");
        System.out.println(biMap);
        System.out.println(biMap.inverse());


        System.out.println();
        Map<String, Integer> map = Maps.newHashMap();
        map.put("q", 1);
        map.put("qq", 2);
        map.put("qqq", 3);
        BiMap<Integer, String> biMap1 = HashBiMap.create(map).inverse();
        System.out.println(map);
        System.out.println(biMap1);
    }

    /**
     * 最基础的工具类使用
     *
     * 比如 Lists、Maps、Collections2 等的静态工厂
     */
    public static void baseTest() {
        // Capacity 能力，最大的存储量
        List<String> exactly100 = Lists.newArrayListWithCapacity(100);
        // Expected 预期，预期大小值，进行优化扩容次数
        List<String> approx100 = Lists.newArrayListWithExpectedSize(100);
        // 同上
        Set<String> approx100Set = Sets.newHashSetWithExpectedSize(100);
        HashMap<Object, Object> hashMap = Maps.newHashMapWithExpectedSize(10);
    }
}
