package com.bfchengnuo.java8;

import java.util.function.*;

/**
 * 展示 Java8 中常用的函数式接口
 *
 * 它们都放在 java.util.function 包里
 *
 * @author Created by 冰封承諾Andy on 2019/6/12.
 *
 * 按照规范，一般它们会被 @FunctionalInterface 标注
 *
 * 为了避免基本数据类型装箱和拆箱带来的性能损耗，有一系列的特化，例如：
 *      以 Consumer 为例，它有 IntConsumer、LongConsumer、DoubleConsumer 分别对应三种基本数据类型。
 *
 * 任何函数式接口都不允许抛出受检异常（checked exception）。
 * 如果你需要 Lambda 表达式来抛出异常，有两种办法：
 *      1.定义一个自己的函数式接口，并声明受检异常；
 *      2.或者把 Lambda 包在一个 try/catch 块中；
 *
 * 系列笔记，see ： https://github.com/bfchengnuo/MyRecord/blob/master/%E7%AC%94%E8%AE%B0/Java/Java8%2B/Java8%E5%AE%9E%E6%88%98%E7%AC%94%E8%AE%B0%EF%BC%88%E4%B8%80%EF%BC%89.md
 */
public class LambdaFunAction {
    public static void main(String[] args) {
        // predicate 描述： T -> boolean , 可以用于延迟加载
        System.out.println(predicateTest(s -> s.length() > 3));

        // consumer 描述： T -> void，可以用于延迟加载
        consumerTest(System.out::println);

        // function 描述： T -> R
        System.out.println(functionTest(String::length));

        // supplier 描述符： () -> T
        System.out.println(supplierTest(() -> "HelloWorld"));

        // UnaryOperator 描述符： T -> T
        System.out.println(unaryOperatorTest("Hello"::concat));

        // BinaryOperator 描述符： (T, T) -> T
        System.out.println(binaryOperatorTest(String::concat));

        // BiPredicate 描述符： (L, R) -> boolean
        System.out.println(biPredicateTest((str, num) -> str.length() > num));

        // BiConsumer 描述符： (T, U) -> void
        biConsumerTest((str, num) -> System.out.println(str.concat("-").concat(String.valueOf(num))));

        // BiFunction 描述符： (T, U) -> R
        System.out.println(biFunctionTest((str, num) -> str.length() < num));

        // 构造函数使用
        // 如果只有一个参数，那么适合 Function 的签名；如果有两个参数那么可以试试 BiFunction 的签名
        Supplier<Object> aNew = Object::new;
        Object obj = aNew.get();

        // 函数复合，例如： 对一个数先加一再乘以二
        // andThen 和 compose 的区别就是 g(f(x)) 和 f(g(x)) 的区别
        Function<Integer, Integer> f = x -> x + 1;
        Function<Integer, Integer> g = x -> x * 2;
        Function<Integer, Integer> finalFun = f.andThen(g);
        int result = finalFun.apply(1);
        System.out.println(result);
    }

    /**
     * predicate 描述： T -> boolean
     * 接受一个类型，返回一个 boolean 类型
     *
     * @param predicate 本例接受一个处理 String 的函数
     */
    private static boolean predicateTest(Predicate<String> predicate) {
        return predicate.test("Call Data");
    }

    /**
     * consumer 描述： T -> void
     * 接受一个类型，并消费它（返回是 void）
     *
     * 也可以做到延时执行的效果
     *
     * @param consumer 本例的 T 以 String 为例，接受一个处理数据 TestData 的函数
     */
    private static void consumerTest(Consumer<String> consumer) {
        consumer.accept("TestData");
    }

    /**
     * function 描述： T -> R
     * 接受一个类型，返回另一个类型
     *
     * @param function 函数接口，本例接受一个 String 返回一个 integer
     * @return 返回的固定测试数据为 TestData
     */
    private static Integer functionTest(Function<String, Integer> function) {
        return function.apply("TestData");
    }

    /**
     * supplier 描述符： () -> T
     * 不接受任何类型，返回一个类型
     */
    private static String supplierTest(Supplier<String> supplier) {
        return supplier.get();
    }

    /**
     * UnaryOperator 描述符： T -> T
     * 接受一个类型，并且返回一个相同的类型
     */
    private static String unaryOperatorTest(UnaryOperator<String> unaryOperator) {
        return unaryOperator.apply("TestData");
    }

    /**
     * BinaryOperator 描述符： (T, T) -> T
     * 接受两个相同的类型，聚合为一个
     */
    private static String binaryOperatorTest(BinaryOperator<String> binaryOperator) {
        return binaryOperator.apply("First", "Second");
    }

    /**
     * BiPredicate 描述符： (L, R) -> boolean
     * 接受两个不同的对象，返回一个布尔值，测试数值： TestData 和 0
     */
    private static boolean biPredicateTest(BiPredicate<String, Integer> biPredicate) {
        return biPredicate.test("TestData", 0);
    }

    /**
     * BiConsumer 描述符： (T, U) -> void
     * 接受两个不同的类型，返回空，消费者的扩展
     * 测试数值： TestData 和 0
     */
    private static void biConsumerTest(BiConsumer<String, Integer> biConsumer) {
        biConsumer.accept("TestData", 0);
    }

    /**
     * BiFunction 描述符： (T, U) -> R
     * 接受两个不同的类型，返回第三个类型，测试数值： TestData 和 0, 返回值为布尔
     */
    private static Boolean biFunctionTest(BiFunction<String, Integer, Boolean> biFunction) {
        return biFunction.apply("TestData", 0);
    }
}
