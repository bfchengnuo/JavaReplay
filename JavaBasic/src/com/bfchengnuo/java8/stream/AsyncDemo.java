package com.bfchengnuo.java8.stream;

import com.bfchengnuo.java8.stream.po.Shop;
import com.bfchengnuo.juc.threadpool.CustomizeCreatePool;

import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

/**
 * 这里是异步相关的内容，提高吞吐量
 *
 * 异步返回对象可以使用 {@link CompletableFuture}
 * 自定义线程池可以看看 {@link com.bfchengnuo.juc.threadpool.CustomizeCreatePool}，配置的规则在下面的连接能找到依据
 *
 * see： https://github.com/bfchengnuo/MyRecord/blob/master/%E7%AC%94%E8%AE%B0/Java/Java8%2B/Java8%E5%AE%9E%E6%88%98%E7%AC%94%E8%AE%B0%EF%BC%88%E5%9B%9B%EF%BC%89%E7%BB%84%E5%90%88%E5%BC%8F%E5%BC%82%E6%AD%A5%E7%BC%96%E7%A8%8B.md
 *
 * @author Created by 冰封承諾Andy on 2019/7/4.
 */
public class AsyncDemo {
    private static List<Shop> shops = Shop.getList();
    private static CustomizeCreatePool customizeCreatePool = new CustomizeCreatePool(10);
    public static void main(String[] args) {

    }

    /**
     * 异步传统的实现方案
     */
    public static void traditionalExample() {
        ExecutorService executor = Executors.newCachedThreadPool();
        // 提交异步任务
        Future<Double> future = executor.submit(AsyncDemo::doSomeLongComputation);

        // 可以做点其他的事情
        // doSomethingElse();

        try {
            // 获取异步操作的结果，如果被阻塞，就等待一秒钟后退出
            // 提供 isDone 方法来判断是否结束
            Double result = future.get(1, TimeUnit.SECONDS);
        } catch (ExecutionException ee) {
            // 计算抛出一个异常
        } catch (InterruptedException ie) {
            // 当前线程在等待过程中被中断
        } catch (TimeoutException te) {
            // 在Future对象完成之前超过已过期
        }
    }
    private static Double doSomeLongComputation() {
        return null;
    }

    /**
     * 假设一种商店提供价格获取的接口，异步第一版
     * Future 是一个暂时还不可知值的处理器，这个值在计算完成后，可以通过调用它的 get 方法取得。
     * 因为这样的设计， getPriceAsync 方法才能立刻返回，给调用线程一个机会，能在同一时间去执行其他有价值的计算任务。
     *
     * 如果发生了异常，会导致线程的死亡，调用 get 方法的客户端也会被永久的阻塞，所以选择一个超时时间的构造方法是不错的
     *
     * @param product 传入参数
     * @return 返回一个“函数”
     */
    public Future<Double> getPriceAsync1(String product) {
        // 用于包含计算的结果
        CompletableFuture<Double> futurePrice = new CompletableFuture<>();
        // 在另一个线程中以异步的方式计算
        new Thread( () -> {
            // calculatePrice 方法是耗时的，使用 sleep 模拟
            double price = calculatePrice(product);
            futurePrice.complete(price);
        }).start();

        // 无需等待，直接返回
        return futurePrice;
    }

    /**
     * 第二版，可以将异常进行抛出传递
     */
    public Future<Double> getPriceAsync2(String product) {
        CompletableFuture<Double> futurePrice = new CompletableFuture<>();
        new Thread( () -> {
            try {
                double price = calculatePrice(product);
                futurePrice.complete(price);
            } catch (Exception ex) {
                // 发生异常则抛出
                futurePrice.completeExceptionally(ex);
            }
        }).start();
        return futurePrice;
    }

    /**
     * 第三版，使用 CompletableFuture 自带的 API
     *
     * supplyAsync 方法接受一个生产者（Supplier）作为参数，返回一个 CompletableFuture 对象，
     * 该对象完成异步执行后会读取调用生产者方法的返回值。
     * 生产者方法会交由 ForkJoinPool 池中的某个执行线程（Executor）运行，
     * 但是你也可以使用 supplyAsync 方法的重载版本，传递第二个参数指定不同的执行线程执行生产者方法。
     *
     * 一般而言，向 CompletableFuture 的工厂方法传递可选参数，指定生产者方法的执行线程是可行的。
     * 同时，它提供了跟第二版同样的错误管理机制，基本是等价的，只不过这样一行代码更加简洁了。
     */
    public Future<Double> getPriceAsync3(String product) {
        return CompletableFuture.supplyAsync(() -> calculatePrice(product));
    }
    private double calculatePrice(String product) {
        return 0;
    }

    /***************************************分割线******************************************/

    /**
     * 免受阻塞的例子
     * 假设此方法签名强制规定
     */
    public static List<String> findPrices1() {
        // 使用 parallelStream 来优化 stream
        return shops.parallelStream()
                .map(shop -> String.format(
                        "%s price is %.2f",
                        shop.getName(),
                        shop.getPrice()))
                .collect(toList());
    }

    /**
     * 理想情况下的异步操作
     * 但是方法签名不对应
     */
    public static List<CompletableFuture<String>>  findPrices2() {
        return shops.stream().map(
                shop -> CompletableFuture.supplyAsync(
                        () -> String.format(
                                "%s price is %.2f",
                                shop.getName(),
                                shop.getPrice()
                        )
                )
        ).collect(toList());
    }

    /**
     * 符合方法前面的异步
     * 对 List 中的所有 future 对象执行 join 操作，一个接一个地等待它们运行结束。
     * PS：  join 不会抛出任何检测到的异常
     *
     * 这里使用了两个不同的 Stream 流水线，而不是在同一个处理流的流水线上一个接一个地放置两个 map 操作，
     * 考虑流操作之间的延迟特性，如果你在单一流水线中处理流，多次被调只能以同步、顺序执行的方式才会成功，这样就和使用同步没啥区别了。
     */
    public static List<String> findPrices3() {
        List<CompletableFuture<String>> priceFutures = shops.stream()
                .map(shop -> CompletableFuture.supplyAsync(
                        () -> shop.getName() + " price is " + shop.getPrice())
                ).collect(Collectors.toList());

        return priceFutures.stream()
                .map(CompletableFuture::join)
                .collect(toList());
    }

    /***************************************分割线******************************************/

    /**
     * 多个异步任务的流水线操作
     * 方案一，传统的同步操作
     */
    public static List<String> findPricesAsync1() {
        return shops.stream()
                // 将每个 shop 对象转换成了一个字符串，该字符串包含了该 shop 中指定商品的价格和折扣代码。
                .map(Shop::getPrice)
                // 对这些字符串进行了解析，在 Quote 对象中对它们进行转换
                .map(Shop::parse)
                // 模拟调用远程的 Discount 服务，计算出最终的折扣价格，并返回该价格及提供该价格商品的 shop
                .map(Shop::applyDiscount)
                .collect(toList());
    }

    /**
     * 使用 CompletableFuture 进行异步流水线操作
     *
     * 对第一步中生成的 CompletableFuture 对象调用它的 thenApply, 让其处理完成后执行转换操作
     * 使用的 thenApply 方法都不会阻塞你代码的执行
     *
     * CompletableFuture API 提供了名为 thenCompose 的方法允许你对两个异步操作进行流水线，
     * 第一个操作完成时，将其结果作为参数传递给第二个操作。
     *
     *
     *
     * 如果我们不做 collect 而是直接返回 Stream<CompletableFuture<String>>， 在第一时间返回而不是等到所有处理完成
     * 通过 name.map(f -> f.thenAccept(System.out::println)) 操作可以让其拿到结果后执行指定的逻辑
     * 如果需要确认全部执行完成，还是要使用 join，例如用 CompletableFuture 数组先收集起来执行 join
     *
     * 和之前看到的 thenCompose 和 thenCombine 方法一样， thenAccept 方法也提供了一个异步版本，名为 thenAcceptAsync 。
     * 异步版本的方法会对处理结果的消费者进行调度，从线程池中选择一个新的线程继续执行，
     * 不再由同一个线程完成 CompletableFuture 的所有任务。
     *
     * 为了避免不必要的上下文切换，避免在等待线程上浪费时间，按实际情况决定是否采用异步版本。
     */
    public static List<String> findPricesAsync2() {
        List<CompletableFuture<String>> priceFutures = shops.stream()
                // 跟之前一样，以异步的方式取得原始价格
                .map(shop ->
                        // one async
                        CompletableFuture.supplyAsync(
                                shop::getPrice,
                                customizeCreatePool.getExecutor()))
                // 存在时，进行转换
                .map(future -> future.thenApply(Shop::parse))
                // 使用另一个异步任务构造申请折扣的 Future
                .map(future -> future.thenCompose(quote ->
                        // two async
                        CompletableFuture.supplyAsync(
                                () -> Shop.applyDiscount(quote),
                                customizeCreatePool.getExecutor())))
                .collect(toList());

        // 等待全部完毕，提取值
        return priceFutures.stream()
                .map(CompletableFuture::join)
                .collect(toList());
    }

    /**
     * 如何将两个 CompletableFuture 对象整合起来?
     * 除了上面有顺序关系的流水线，没有关系的如何整合？（你不希望等到第一个任务完全结束才开始第二项任务）
     *
     * 使用 thenCombine 方法，它接收名为 BiFunction 的第二参数，
     * 这个参数定义了当两个 CompletableFuture 对象完成计算后，结果如何合并。
     * 同 thenCompose 方法一样，thenCombine 方法也提供有一个 Async 的版本。
     */
    public static void asyncExpansion() {
        Shop shop = new Shop("test", 20D);

        Future<Double> future =
                // 第一个任务：查询商品价格
                CompletableFuture.supplyAsync(shop::getPrice)
                        .thenCombine(
                                // 第二个任务：查询汇率(伪)
                                CompletableFuture.supplyAsync(() -> 0.6),
                                // 结果合并
                                (price, rate) -> price * rate
                        );
    }
}
