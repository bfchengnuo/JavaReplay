package com.bfchengnuo.diveinspring.assemble.spring.condition;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by 冰封承諾Andy on 2019/5/4.
 *
 * 条件装配的示例，也分为两种，基于配置（Profile）和基于编程（Conditional）；
 * 因为 Conditional 更加的灵活，所以 Spring4 之后大量使用
 *
 * {@link CalcService}
 */
@ComponentScan(basePackages = "com.bfchengnuo.diveinspring.assemble.spring.condition")
public class CalcServiceBootstrap {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = new SpringApplicationBuilder(CalcServiceBootstrap.class)
                .web(WebApplicationType.NONE)
                .profiles("java7")
                // .profiles("java8")
                .run(args);

        CalcService calcService = context.getBean(CalcService.class);
        System.out.println(calcService.sum(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));

        context.close();
    }
}
