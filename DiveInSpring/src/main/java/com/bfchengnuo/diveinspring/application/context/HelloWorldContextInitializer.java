package com.bfchengnuo.diveinspring.application.context;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

/**
 * Created by 冰封承諾Andy on 2019/5/5.
 *
 * 测试加载应用上下文初始器（SpringApplication 准备阶段）;
 * 实现 ApplicationContextInitializer 接口，并且在 spring.factory 文件中进行声明，然后 Spring 会通过工厂来 load 进去
 * 此时 Spring 的上下文还没有完全初始化好，所以如果此时调用 getBean 可能会有问题。
 *
 * 使用 @Order 进行排序，默认是最低优先级
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
public class HelloWorldContextInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
    @Override
    public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
        System.out.println("HelloWorldContextInitializer.id = " + configurableApplicationContext.getId());
    }
}
