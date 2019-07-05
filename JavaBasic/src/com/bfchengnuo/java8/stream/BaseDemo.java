package com.bfchengnuo.java8.stream;

import com.bfchengnuo.java8.stream.po.Dish;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.reducing;
import static java.util.stream.Collectors.toList;

/**
 * J8 新加入的 Stream API 简单使用
 *
 * 集合讲的是数据，流讲的是计算, 从有序集合生成流时会保留原有的顺序
 * 流的数据处理功能支持类似于数据库的操作，如 filter、map、reduce、find 、match、sort、distinct、limit、skip 等，
 * 可以顺序执行也可以并行执行（stream 和 parallelStream）
 *
 * 很多流操作本身会返回一个流，这样多个操作就可以连接起来，同时也需要主要流的扁平化，并且流是内部迭代
 *
 * 和迭代器类似，流只能遍历一次。遍历完之后，我们就说这个流已经被消费掉了。
 * 你可以认为流是在时间上分布的一组值，而集合是在空间上分布的。
 *
 * 流是在概念上固定的数据结构（你不能添加或删除元素），其元素则是按需计算的，
 * 从另一个角度来说，流就像是一个延迟创建的集合：只有在消费者要求的时候才会计算值，
 * 与此相反，集合则是急切创建的，例如你可以用流来构建质数集合，但是用集合却做不到。
 *
 * 流操作一般分为两类：
 *      中间操作 filter 、 map 和 limit 可以连成一条流水线
 *      终端操作 collect 触发流水线执行并关闭它
 *
 * 除非流水线上触发一个终端操作，否则中间操作不会执行任何处理——它们很懒，
 * 这是因为中间操作一般都可以合并起来，在终端操作时一次性全部处理（极好的优化，例如 limit 就是一种短路操作）。
 * 流的流水线背后的理念类似于构建器模式，最后调用的 built 方法就类似于流的终端操作
 *
 * 构建流的几种方式：
 *      Stream.of()
 *      Collection.stream()
 *      Arrays.stream()
 *      Files.lines
 *      Stream.iterate 和 Stream.generate 从函数生成流
 *
 * Java8 实战的示例代码：
 * see: https://github.com/java8/Java8InAction
 *
 * 分组分区相关请参考： {@link GroupDemo}
 *
 * @author Created by 冰封承諾Andy on 2019/6/27.
 */
public class BaseDemo {
    public static void main(String[] args) {
        // 基础使用
        example1();
        // 映射 map 与流的扁平化
        example2();
        // 归约操作
        reduceExample();
        // 从函数生成流
        generateStream();
        // 收集你所需的数据
        collectExample();
    }

    private static void example1() {
        List<Dish> menu = Arrays.asList(
                new Dish("pork", false, 800, Dish.Type.MEAT),
                new Dish("beef", false, 700, Dish.Type.MEAT),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("french fries", true, 530, Dish.Type.OTHER),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("season fruit", true, 120, Dish.Type.OTHER),
                new Dish("pizza", true, 550, Dish.Type.OTHER),
                new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish("salmon", false, 450, Dish.Type.FISH)
        );

        List<String> lowCaloricDishesName = menu.stream()
                // 选出400卡路里以下的菜肴
                .filter(d -> d.getCalories() < 400)
                // 按照卡路里排序
                .sorted(comparing(Dish::getCalories))
                // 提取名称
                .map(Dish::getName)
                // 收集到 List 中
                .collect(toList());

        System.out.println(lowCaloricDishesName);
    }

    private static void example2() {
        List<String> words = Arrays.asList("Hello", "World");
        List<String> uniqueCharacters = words.stream()
                .map(w -> w.split(""))
                // 使用 flatMap 方法的效果是，各个数组并不是分别映射成一个流，而是映射成流的内容。
                // 所有使用 map(Arrays::stream) 时生成的单个流都被合并起来，即扁平化为一个流。
                .flatMap(Arrays::stream)
                .distinct()
                .collect(Collectors.toList());
        System.out.println(uniqueCharacters);


        // 给定两个数字列表，如何返回所有的数对呢?
        // in: [1, 2, 3]  [3, 4]
        // out: [(1, 3), (1, 4), (2, 3), (2, 4), (3, 3), (3, 4)]
        List<Integer> numbers1 = Arrays.asList(1, 2, 3);
        List<Integer> numbers2 = Arrays.asList(3, 4);
        List<int[]> pairs = numbers1.stream()
                .flatMap(i -> numbers2.stream().map(j -> new int[]{i, j}))
                .collect(toList());
        System.out.println(pairs);
    }

    /**
     * 归约操作 reduce， 数值流
     *
     * reduce 接受两个参数：一个初始值，这里是0；一个 BinaryOperator<T> 来将两个元素结合起来产生一个新值。
     * reduce 还有一个重载的变体，它不接受初始值，但是会返回一个 Optional 对象。
     *
     * map 和 reduce 的连接通常称为 map-reduce 模式，因Google用它来进行网络搜索而出名，因为它很容易并行化
     */
    private static void reduceExample() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        int sum = numbers.stream().reduce(0, (a, b) -> a + b);
        int sum2 = numbers.stream().reduce(0, Integer::sum);

        // rangeClosed 与 range 的区别，是开区间与闭区间的问题
        IntStream evenNumbers = IntStream.rangeClosed(1, 100).filter(n -> n % 2 == 0);
        System.out.println(evenNumbers);
    }

    /**
     * 使用函数生成流，不管 iterate 还是 generate，都是接受一个生成数的函数
     */
    private static void generateStream() {
        // 0 2 4 6
        Stream.iterate(0, n -> n + 2)
                .limit(10)
                .forEach(System.out::println);

        Stream.generate(Math::random)
                .limit(5)
                .forEach(System.out::println);
    }

    /**
     * 用流收集你所需的数据
     *
     * 常用的方法：
     *      groupingBy
     *      toList、toSet
     *      counting
     *      maxBy、minBy
     *      summarizingInt (一次返回最大值、最小值、数量等信息)
     *      joining （非字符串调用 toString 方法）
     *
     * 万能方法： Collectors.reducing，一般来说 reducing 需要三个参数：
     *
     * 1.归约操作的起始值，也是流中没有元素时的返回值，所以很显然对于数值和而言 0 是一个合适的值。
     * 2.一个汇总之类的转换函数，可用于标识要处理对象的属性
     * 3.一个 BinaryOperator，将两个项目累积成一个同类型的值的积累函数
     *
     * reduce 方法旨在把两个值结合起来生成一个新值，它是一个不可变的归约。
     */
    @SuppressWarnings({"SimplifyStreamApiCallChains", "AlibabaRemoveCommentedCode", "Convert2MethodRef"})
    private static void collectExample() {
        List<Dish> menu = Dish.menu;

        // 简写形式： int totalCalories = menu.stream().map(Dish::getCalories).reduce(0, (i, j) -> i + j);
        int totalCalories = menu.stream().collect(reducing(
                0,
                Dish::getCalories,
                (i, j) -> i + j)
        );

        // 单参形式获得最高热量的菜
        Optional<Dish> mostCalorieDish =
                // 简写形式： menu.stream().reduce((d1, d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2);
                menu.stream().collect(reducing(
                        (d1, d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2)
                );

        // 实现 toListCollector 的工作
        Stream<Integer> stream = Stream.of(1, 2, 3, 4, 5, 6);
        List<Integer> numbers = stream.reduce(
                new ArrayList<>(),
                // l 为定义的初始空 list， e 为迭代的 number
                (List<Integer> l, Integer e) -> {
                    l.add(e);
                    return l;
                },
                // ll 为上一步的 list，l2 为这一步的 list
                (List<Integer> l1, List<Integer> l2) -> {
                    l1.addAll(l2);
                    return l1;
                }
        );
    }
}
