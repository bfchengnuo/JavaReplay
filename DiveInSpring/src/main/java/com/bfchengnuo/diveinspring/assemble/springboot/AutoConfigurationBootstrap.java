package com.bfchengnuo.diveinspring.assemble.springboot;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Created by 冰封承諾Andy on 2019/5/4.
 *
 * SpringBoot 自动装配引导类
 *
 * SB 自动装配三大步：
 *      1.开启自动装配 @EnableAutoConfiguration
 *      2.编写 XXXAutoConfiguration
 *      3.将编写的 XXXAutoConfiguration 配置到 META-INF/spring.factories
 */
@EnableAutoConfiguration
public class AutoConfigurationBootstrap {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = new SpringApplicationBuilder(AutoConfigurationBootstrap.class)
                .web(WebApplicationType.NONE)
                .run(args);

        String str = context.getBean("helloWorld", String.class);
        System.out.println(str);

        context.close();
    }
}
