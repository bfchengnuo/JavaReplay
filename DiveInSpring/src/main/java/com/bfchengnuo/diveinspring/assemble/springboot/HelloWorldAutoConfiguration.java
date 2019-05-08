package com.bfchengnuo.diveinspring.assemble.springboot;

import com.bfchengnuo.diveinspring.assemble.spring.assemble.EnableHelloWorld;
import com.bfchengnuo.diveinspring.assemble.spring.condition.ConditionalOnSystemProperty;
import org.springframework.context.annotation.Configuration;

/**
 * Created by 冰封承諾Andy on 2019/5/4.
 *
 * 编写自动配置类，使用 Spring 模式注解 @Configuration 装配，直接在里面定义 @bean 的形式
 *
 * 可以使用 @Enable 模块装配来简化配置（一群功能的配置实现）；
 *       EnableHelloWorld -> Selector -> HelloWorldConfiguration -> HelloWorld Str
 * 可以使用 @Condition 条件装配；
 * 他们是可以共用的。
 */
@Configuration
@EnableHelloWorld
@ConditionalOnSystemProperty(name = "user.name", val = "loli")
public class HelloWorldAutoConfiguration {
}
