package com.bfchengnuo.uselibraries.guava;

import com.google.common.collect.Sets;

import java.util.Set;

/**
 * @author 冰封承諾Andy
 * @date 2020/10/16
 */
public class CollectionsCalc {
    public static void main(String[] args) {
        Set<Integer> sets = Sets.newHashSet(1, 2, 3, 4, 5, 6);
        Set<Integer> sets2 = Sets.newHashSet(3, 4, 8, 9);
        // 交集
        System.out.println("交集为：");
        Sets.SetView<Integer> intersection = Sets.intersection(sets, sets2);
        System.out.println(intersection);

        // 差集
        System.out.println("差集为：");
        Sets.SetView<Integer> diff = Sets.difference(sets, sets2);
        System.out.println(diff);

        // 并集
        System.out.println("并集为：");
        Sets.SetView<Integer> union = Sets.union(sets, sets2);
        System.out.println(union);
    }
}
