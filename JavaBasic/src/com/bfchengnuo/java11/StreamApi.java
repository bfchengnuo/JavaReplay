package com.bfchengnuo.java11;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author 冰封承諾Andy
 * @date 2020/5/16
 */
public class StreamApi {
    public static void main(String[] args) {
        // 可以使用 null 来创建一个流
        System.out.println(Stream.ofNullable(null).count());

        // 区别与 match
        List<Integer> collect1 = Stream.of(1, 2, 3, 2, 1)
                // 遇到 false 停止丢弃，保留之后的内容
                .dropWhile(n -> n < 3)
                .collect(Collectors.toList());

        List<Integer> collect2 = Stream.of(1, 2, 3, 2, 1)
                // 遇到条件为 false 后即停止，然后返回
                .takeWhile(n -> n < 3)
                .collect(Collectors.toList());
        System.out.println(collect1);
        System.out.println(collect2);

        // 对迭代流的改进
        // 创建一个无限流，起始值是 1，后面每一项都在前一项的基础上 * 2 + 1，所以需要 limit
        Stream.iterate(1, t -> 2 * t + 1).limit(10).forEach(System.out::println);
        // 新版增加了一个限制条件，不再无限
        Stream.iterate(1, t -> t < 1000, t -> 2 * t + 1);
    }
}
