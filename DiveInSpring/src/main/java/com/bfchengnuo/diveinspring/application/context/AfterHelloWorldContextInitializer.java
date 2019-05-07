package com.bfchengnuo.diveinspring.application.context;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.Ordered;

/**
 * Created by 冰封承諾Andy on 2019/5/5.
 *
 * 通过 order 来控制在 {@link HelloWorldContextInitializer} 之后被加载；
 * 除了使用注解还可以使用 Ordered 接口，效果是一样的。
 */
public class AfterHelloWorldContextInitializer implements ApplicationContextInitializer, Ordered {
    @Override
    public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
        System.out.println("AfterHelloWorldContextInitializer.id = " + configurableApplicationContext.getId());
    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }
}
