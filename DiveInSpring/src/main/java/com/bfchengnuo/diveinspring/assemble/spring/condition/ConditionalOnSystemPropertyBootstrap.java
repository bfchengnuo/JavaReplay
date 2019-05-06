package com.bfchengnuo.diveinspring.assemble.spring.condition;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * Created by 冰封承諾Andy on 2019/5/4.
 *
 * 系统属性条件判断启动类
 */
public class ConditionalOnSystemPropertyBootstrap {

    @Bean
    @ConditionalOnSystemProperty(name = "user.name", val = "loli")
    public String helloWorld() {
        return "Hello Condition";
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext context = new SpringApplicationBuilder(ConditionalOnSystemPropertyBootstrap.class)
                .web(WebApplicationType.NONE)
                .run(args);

        String str = context.getBean("helloWorld", String.class);
        System.out.println(str);

        context.close();
    }
}
