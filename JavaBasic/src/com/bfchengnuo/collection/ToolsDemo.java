package com.bfchengnuo.collection;

import java.util.AbstractList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by 冰封承諾Andy on 2019/4/29.
 *
 * 集合框架下常用的一些工具类；
 *
 * J9+ 之后可以使用 List.of()  Set.of() 等一系列 of 工厂方法得到一个不可变的集合对象，并且是只读的；
 * J5+ 可以用 CopyOnWriteArrayList，它也是不可变的；
 * 关于只读对象，可以了解下 Immutable 相关的类，ImmutableCollections 推荐 J11
 *
 * 在设计方面，作为集合方法的入参：
 *      1.如果能用 Iterable 尽量用
 *      2.其次是 Collection
 *      3.再者是 List Set 这些
 *      禁止使用具体类型，例如 ArrayList
 */
public class ToolsDemo {
    public static void main(String[] args) {
        // Arrays 返回的 List 是只读的（内部的一个 ArrayList 实现）
        List<Integer> list = Arrays.asList(1, 2, 3, 4);
        // 会报错
        // list.add(0);

        // 但是可以使用 set 来进行修改
        list.set(0, 6);
        list.forEach(System.out::println);

        // 断言, 内部的 ArrayList 没有重写 AbstractList 的修改方法，所以只读
        assert list instanceof AbstractList;

        // Java 9 + of 工厂方法，返回 Immutable 对象
        // list = List.of(1, 2, 3, 4, 5);
        // Set<Integer> set = Set.of(1, 2, 3, 4, 5);
        // Map<Integer, String> map = Map.of(1, "A");
    }
}
