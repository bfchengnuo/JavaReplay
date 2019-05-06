package com.bfchengnuo.diveinspring.assemble.spring.assemble;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by 冰封承諾Andy on 2019/5/3.
 *
 * 测试用装配类
 */
@Configuration
public class HelloWorldConfiguration {
    /**
     * 默认方法名即为 bean 的名称
     */
    @Bean
    public String helloWorld() {
        return "Hello World!";
    }
}
