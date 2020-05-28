package com.bfchengnuo.java11;

import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;
import java.util.concurrent.TimeUnit;

/**
 * Java9 Flow API 或者叫 响应式流（Reactive Streams) API
 * 同样基于生产者消费者模型，区别是它可以让双方进行通讯，消费多少让给多少，不流失不浪费....
 * 响应式（反应式）编程基础
 *
 * @author 冰封承諾Andy
 * @date 2020/5/27
 */
public class FlowDemo {
    public static void main(String[] args) throws InterruptedException {
        // 定义发布者，使用 JDK 自带的 SubmissionPublisher 它实现了 Publisher 接口
        SubmissionPublisher<Integer> publisher = new SubmissionPublisher<>();

        // 定义订阅者
        Flow.Subscriber<Integer> subscriber = new Flow.Subscriber<>() {
            private Flow.Subscription subscription;

            @Override
            public void onSubscribe(Flow.Subscription subscription) {
                // 建立关系的时候调用
                this.subscription = subscription;

                // 请求指定数量的数据
                this.subscription.request(1);
            }

            @Override
            public void onNext(Integer item) {
                // 数据到来的时候触发
                System.out.println("接收到数据：" + item);

                // 调节的关键
                this.subscription.request(1);
                // 停止接收数据
                // this.subscription.cancel();
            }

            @Override
            public void onError(Throwable throwable) {
                throwable.printStackTrace();
                this.subscription.cancel();
            }

            @Override
            public void onComplete() {
                // 发布者关闭的时候触发, close
                System.out.println("done.");
            }
        };

        // 这里可以定义一个处理器（），然后让发布者与处理器实现关联，处理器再与消费者关联
        // 实现中转的目的，类似过滤器
        // MyProcessor myProcessor = new MyProcessor();
        // publisher.subscribe(myProcessor);
        // myProcessor.subscribe(subscriber);

        // 发布者与订阅者建立关系
        publisher.subscribe(subscriber);

        // 生产数据
        // 缓存池满了就会被阻塞
        publisher.submit(233);

        // 关闭发布者，应该放到 try...resource
        // 需要实现 AutoCloseable 接口
        publisher.close();

        // 仅供测试
        TimeUnit.SECONDS.sleep(5);
    }

    /**
     * 处理器示范
     * 与消费者类似，不具体实现
     */
    static class MyProcessor extends SubmissionPublisher<String> implements Flow.Processor<Integer, String>{
        private Flow.Subscription subscription;

        @Override
        public void onSubscribe(Flow.Subscription subscription) {
            this.subscription = subscription;
            subscription.request(1);
        }

        @Override
        public void onNext(Integer item) {
            // 可以做过滤

            this.submit(item.toString());
            this.subscription.request(1);
        }

        @Override
        public void onError(Throwable throwable) {
            this.subscription.cancel();
        }

        @Override
        public void onComplete() {

        }
    }
}
