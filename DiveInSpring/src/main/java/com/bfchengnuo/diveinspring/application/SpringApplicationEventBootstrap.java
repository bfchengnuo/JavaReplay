package com.bfchengnuo.diveinspring.application;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by 冰封承諾Andy on 2019/5/7.
 * <p>
 * 基于 Spring 的事件监听
 *
 * 下面的示例代码中的自定义监听器会收到五次事件：
 *      ContextRefreshedEvent
 *      PayloadApplicationEvent（Hello World）
 *      PayloadApplicationEvent（Hello World Again）
 *      SpringApplicationEventBootstrap（2019）
 *      ContextClosedEvent
 *
 * SpringBoot 通过 SpringApplicationRunListener 的实现类 EventPublishingRunListener
 * 利用 SpringFramework 事件 API ，广播 Spring Boot 事件
 *
 * see https://github.com/bfchengnuo/MyRecord/blob/master/%E7%AC%94%E8%AE%B0/Java/Spring%E5%AE%B6%E6%97%8F/SB_depth.md#spring-framework-%E4%BA%8B%E4%BB%B6%E7%9B%91%E5%90%AC%E5%99%A8%E7%BC%96%E7%A8%8B%E6%A8%A1%E5%9E%8B
 */
public class SpringApplicationEventBootstrap {
    public static void main(String[] args) {
        // 创建上下文
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        // 添加自定义的应用事件监听器
        context.addApplicationListener(
                (applicationEvent) ->
                        System.out.println("监听到事件：" + applicationEvent));

        // 启动上下文
        context.refresh();

        // 发送事件
        context.publishEvent("Hello World");
        context.publishEvent("Hello World Again");
        context.publishEvent(new ApplicationEvent("2019") {
        });

        context.close();
    }
}
