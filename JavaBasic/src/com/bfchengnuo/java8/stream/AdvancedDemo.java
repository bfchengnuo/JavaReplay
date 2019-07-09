package com.bfchengnuo.java8.stream;

import java.util.List;
import java.util.Spliterator;
import java.util.function.Function;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * 关于 Stream API 的进阶使用
 * 并行与 ForkJoin （分支/合并框架）
 *
 * 把顺序流转成并行流轻而易举，但却不一定是好事，并行流并不总是比顺序流快，所以测试一下很有必要
 * 对于较小的数据量，选择并行流几乎从来都不是一个好的决定
 * 要考虑流背后的数据结构是否易于分解
 *
 * 匿名类和 Lambda 表达式中的 this 和 super 的含义是不同的。
 * 在匿名类中， this 代表的是类自身，但是在 Lambda 中，它代表的是包含类。
 * 其次，匿名类可以屏蔽包含类的变量，而 Lambda 表达式不能（会编译错误）。
 *
 * 在涉及重载的上下文里，将匿名类转换为 Lambda 表达式可能导致最终的代码更加晦涩
 * 在无法确定类型的情况下，可以使用显式类型转换标识
 *
 * @see com.bfchengnuo.juc.parallel.ActionFun
 * 感兴趣可以再看看 J8 中的新接口 {@link Spliterator} 用于遍历数据源中的元素，但它是为了并行执行而设计的.
 * see: https://github.com/bfchengnuo/MyRecord/blob/master/%E7%AC%94%E8%AE%B0/Java/Java8%2B/Java8%E5%AE%9E%E6%88%98%E7%AC%94%E8%AE%B0%EF%BC%88%E4%B8%89%EF%BC%89.md
 *
 * @author Created by 冰封承諾Andy on 2019/7/2.
 */
public class AdvancedDemo {
    public static void main(String[] args) {
        parallelTest();
        processingExample();

        peekExample();
    }

    /**
     * 对顺序流调用 parallel 方法可以将其转换为并行流
     *
     * 对顺序流调用 parallel 方法并不意味着流本身有任何实际的变化。
     * 它在内部实际上就是设了一个 boolean 标志，表示你想让调用 parallel 之后进行的所有操作都并行执行
     *
     * 你只需要对并行流调用 sequential 方法就可以把它变成顺序流（在需要顺序执行的地方）
     * 但最后一次 parallel 或 sequential 调用会影响整个流水线，所以不要想着混用
     *
     * 并行流内部使用了默认的 ForkJoinPool （分支/合并框架），它默认的线 程 数量就是你的处理器数量
     */
    private static void parallelTest() {
        Long sum = Stream.iterate(1L, i -> i + 1)
                .limit(10)
                .parallel()
                .reduce(0L, Long::sum);

        System.out.println(sum);

        // 无自动装箱开销
        long reduce = LongStream.rangeClosed(1, 10)
                .parallel()
                .reduce(0L, Long::sum);

        System.out.println(reduce);
    }

    /**
     * 责任链模式调用演示
     */
    private static void processingExample() {
        // 初始化及其调用
        AbstractProcessingObject<String> p1 = new HeaderTextAbstractProcessing();
        AbstractProcessingObject<String> p2 = new SpellCheckerAbstractProcessing();
        // p1 执行完毕后，将结果作为 p2 的参数继续执行
        p1.setSuccessor(p2);

        String result = p1.handle("Aren't labdas really sexy?!!");
        System.out.println(result);

        /*
         * 尝试使用 Lambda
         */
        UnaryOperator<String> headerProcessing =
                (String text) -> "From Raoul, Mario and Alan: " + text;
        UnaryOperator<String> spellCheckerProcessing =
                (String text) -> text.replaceAll("labda", "lambda");
        // 将前两个操作连起来
        Function<String, String> pipeline =
                headerProcessing.andThen(spellCheckerProcessing);

        // 执行
        result = pipeline.apply("Aren't labdas really sexy?!!");
        System.out.println(result);
    }

    /**
     * peek 的设计初衷就是在流的每个元素恢复运行之前，插入执行一个动作。
     */
    private static void peekExample() {
        List<Integer> numbers = IntStream.rangeClosed(1, 10).boxed().collect(Collectors.toList());

        List<Integer> result = numbers.stream()
                .peek(x -> System.out.println("from stream: " + x))
                .map(x -> x + 17)
                .peek(x -> System.out.println("after map: " + x))
                .filter(x -> x % 2 == 0)
                .peek(x -> System.out.println("after filter: " + x))
                .limit(3)
                .peek(x -> System.out.println("after limit: " + x))
                .collect(Collectors.toList());

        System.out.println(result);
    }






    /**
     * 责任链模式 Demo
     */
    public static abstract class AbstractProcessingObject<T> {
        AbstractProcessingObject<T> successor;
        void setSuccessor(AbstractProcessingObject<T> successor){
            this.successor = successor;
        }

        /**
         * 如何进行工作处理
         * @param input 输入参数
         * @return 输出参数，与输入相同类型，便于链式操作
         */
        T handle(T input){
            // 执行处理逻辑
            T r = handleWork(input);
            // 当链式的下一个不为空时，继续处理
            if(successor != null){
                return successor.handle(r);
            }
            return r;
        }

        /**
         * 需要实现的具体处理逻辑
         * @param input 输入内容
         * @return 处理后的输出，与输入相同类型，才可以链式
         */
        abstract protected T handleWork(T input);
    }

    /**
     * 调用示例
     * 首先定义两个处理链
     */
    public static class HeaderTextAbstractProcessing extends AbstractProcessingObject<String> {
        @Override
        public String handleWork(String text){
            return "From Raoul, Mario and Alan: " + text;
        }
    }
    public static class SpellCheckerAbstractProcessing extends AbstractProcessingObject<String> {
        @Override
        public String handleWork(String text){
            return text.replaceAll("labda", "lambda");
        }
    }
}
