package com.bfchengnuo.diveinspring.application.listener;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * @author Created by 冰封承諾Andy on 2019/5/7.
 *
 * SpringApplication 运行监听器，执行顺序优先级很高，见 SpringApplication 源码
 *
 * SpringApplicationRunListener 通过工厂的方式进行加载（spring.factory），默认只有 EventPublishingRunListener 实现
 * 可以使用 ordered 进行排序
 */
public class HelloWorldRunListener implements SpringApplicationRunListener {

    /**
     * 必须要有这个构造的签名，会用到，否则报错
     * @param application
     * @param args
     */
    public HelloWorldRunListener(SpringApplication application, String[] args) {
    }

    @Override
    public void starting() {
        System.out.println("HelloWorldRunListener#starting run...");
    }

    @Override
    public void environmentPrepared(ConfigurableEnvironment environment) {

    }

    @Override
    public void contextPrepared(ConfigurableApplicationContext context) {

    }

    @Override
    public void contextLoaded(ConfigurableApplicationContext context) {

    }

    @Override
    public void started(ConfigurableApplicationContext context) {

    }

    @Override
    public void running(ConfigurableApplicationContext context) {

    }

    @Override
    public void failed(ConfigurableApplicationContext context, Throwable exception) {

    }
}
