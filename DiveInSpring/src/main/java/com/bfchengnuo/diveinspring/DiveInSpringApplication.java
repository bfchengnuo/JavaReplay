package com.bfchengnuo.diveinspring;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.context.WebServerApplicationContext;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;

/**
 * 自动生成，保留用，无意义
 * 在 @SpringBootApplication 中内含 @EnableAutoConfiguration
 *
 * 补充：查看嵌入的 web 服务器类型
 */
@SpringBootApplication
public class DiveInSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(DiveInSpringApplication.class, args);
    }

    /**
     * SpringBoot 启动后会回调 ApplicationRunner 的 run 方法；
     *
     * 注意，在非 Web 项目下 WebServerApplicationContext 是注入失败的，没有这个实现类
     * @param context
     * @return
     */
    @Bean
    public ApplicationRunner runner(WebServerApplicationContext context) {
        return args ->
                System.out.println("当前 WebServer 实现类为：" + context.getWebServer().getClass().getName());
    }

    /**
     * 更好的解决方案就是监听 WebServerInitializedEvent 事件，
     * 当 web 服务器初始化后会执行
     * @param event
     */
    @EventListener(WebServerInitializedEvent.class)
    public void onWebServerReady(WebServerInitializedEvent event) {
        System.out.println("[Event] 当前 WebServer 实现类为：" + event.getWebServer().getClass().getName());
    }
}
