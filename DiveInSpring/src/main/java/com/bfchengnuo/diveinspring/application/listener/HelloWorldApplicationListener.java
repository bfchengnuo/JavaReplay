package com.bfchengnuo.diveinspring.application.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

/**
 * Created by 冰封承諾Andy on 2019/5/5.
 * <p>
 * 应用事件监听器的测试，其中 SpringBoot 的事件是建立在 Spring 事件的基础上的；
 * 关键接口有 ApplicationListener，具体监听的事件通过泛型来确定。
 * <p>
 * 方法与上下文的初始化类似，实现接口，配置到 spring.factory，然后可以使用 order 来进行排序;
 * <p>
 * 这里以 ContextRefreshedEvent 这个事件为例，表示上下文初始化完毕。
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
public class HelloWorldApplicationListener implements ApplicationListener<ContextRefreshedEvent> {
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        System.out.println("事件监听 - HelloWorldApplicationListener.id - " + contextRefreshedEvent.getApplicationContext().getId()
                + "，Timestamp - " + contextRefreshedEvent.getTimestamp());
    }
}
