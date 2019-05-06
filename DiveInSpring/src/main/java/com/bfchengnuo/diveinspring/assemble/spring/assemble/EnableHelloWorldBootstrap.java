package com.bfchengnuo.diveinspring.assemble.spring.assemble;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by 冰封承諾Andy on 2019/5/3.
 *
 * 模块自动装配的测试启动类
 * 主要是通过 @EnableXXX 来实现自动装配
 *
 * {@link EnableHelloWorld}
 */
@ComponentScan(basePackages = "com.bfchengnuo.diveinspring.assemble.spring.assemble")
public class EnableHelloWorldBootstrap {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = new SpringApplicationBuilder(EnableHelloWorldBootstrap.class)
                .web(WebApplicationType.NONE)
                .run(args);

        String str = context.getBean("helloWorld", String.class);
        System.out.println(str);

        context.close();
    }
}
