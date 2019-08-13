package com.bfchengnuo.collection;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 使用 J8 进行多字段的去重示例
 *
 * 要求： 实体中 id1 与 id2 不能存在重复数据。
 * 第一种： 结果中的集合 id1 与 id2 不能存在重复
 * 第二种： 原始数据 id1 与 id2 只要存在重复就去除
 *
 * @author Created by 冰封承諾Andy on 2019/7/8.
 */
public class RemoveDuplicatesDemo {
    public static void main(String[] args) {
        List<User> userList = new ArrayList<>();
        userList.add(new User("123", "456"));
        userList.add(new User("111", "000"));
        userList.add(new User("222", "999"));
        userList.add(new User("333", "000"));
        userList.add(new User("111", "456"));
        userList.add(new User("123", "456"));

        // 第一种方案
        List<User> collect = theOne(userList);
        // 第二种方案
        List<User> result = theSecond(userList);

        System.out.println(collect);
        System.out.println(result);
    }

    private static List<User> theSecond(List<User> userList) {
        // 收集 id1 重复的 id
        Set<String> collect1 = userList.stream()
                // 过滤 id1 的重复内容
                .collect(Collectors.groupingBy(User::getId1))
                .values().stream()
                .filter(list -> list.size() > 1)
                .flatMap(Collection::stream)
                // 收集为 set
                .map(User::getId1)
                .collect(Collectors.toSet());

        // 收集 id2 重复的 id
        Set<String> collect2 = userList.stream()
                // 过滤 id1 的重复内容
                .collect(Collectors.groupingBy(User::getId2))
                .values().stream()
                .filter(list -> list.size() > 1)
                .flatMap(Collection::stream)
                // 收集为 set
                .map(User::getId2)
                .collect(Collectors.toSet());

        // 如果需要重复的数据使用下面的分组收集，如果需要确定行号，使用 for i 遍历
        Map<Boolean, List<User>> collect = userList.stream()
                .collect(Collectors.groupingBy(
                        u -> !collect1.contains(u.getId1()) && !collect2.contains(u.getId2())));

        // 过滤两者都不重复的
        return userList.stream()
                .filter(u -> !collect1.contains(u.getId1()) && !collect2.contains(u.getId2()))
                .collect(Collectors.toList());
    }

    private static List<User> theOne(List<User> userList) {
        return userList.stream()
                    // 过滤 id1 的重复内容
                    .collect(Collectors.groupingBy(User::getId1))
                    .values().stream()
                    .filter(list -> list.size() == 1)
                    .flatMap(Collection::stream)
                    // 过滤 id2 的重复内容
                    .collect(Collectors.groupingBy(User::getId2))
                    .values().stream()
                    .filter(list -> list.size() == 1)
                    .flatMap(Collection::stream)
                    // 收集为 list
                    .collect(Collectors.toList());
    }


    private static class User {
        private String id1;
        private String id2;

        public User(String id1, String id2) {
            this.id1 = id1;
            this.id2 = id2;
        }

        public String getId1() {
            return id1;
        }

        public void setId1(String id1) {
            this.id1 = id1;
        }

        public String getId2() {
            return id2;
        }

        public void setId2(String id2) {
            this.id2 = id2;
        }
    }
}

