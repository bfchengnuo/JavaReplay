package com.bfchengnuo.java11;

import java.util.List;
import java.util.Map;

/**
 * 新增的集合方面的 API
 *
 * @author 冰封承諾Andy
 * @date 2020/5/15
 */
public class CollectionApi {
    public static void main(String[] args) {
        // 创建的是不可变集合
        var list = List.of("A", "B", "C");
        // copy 的结果也是不可变
        var copy = List.copyOf(list);
        // true， 如果 copy 的不是不可变对象，会得到一个新实例
        System.out.println(list == copy);

        var map = Map.of("A", 1, "B", 2);
        System.out.println(map);
    }
}
