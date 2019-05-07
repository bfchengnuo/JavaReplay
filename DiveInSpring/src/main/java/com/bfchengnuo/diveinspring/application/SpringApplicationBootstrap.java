package com.bfchengnuo.diveinspring.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by 冰封承諾Andy on 2019/5/4.
 *
 * 验证 SpringApplication 的运行方式。
 * 可参考 SpringApplication 类的构造方法。
 *
 * SpringApplication 准备阶段：
 *      配置 SpringBean 来源（Java 配置 or XML 配置，Spring Boot 使用 BeanDefinitionLoader 读取确定）
 *      推断 Web 应用类型，根据主引导类
 *          根据当前应用 ClassPath 中是否存在相关实现类来推断 Web 应用的类型；
 *          主引导类的判断通过线程的执行栈，向上搜寻 main 方法。
 *      通过 SpringFactoriesLoader 和 spring.factories 文件加载应用上下文初始器（ApplicationContextInitializer）和应用事件监听器（ApplicationListener）
 *
 * SpringApplication 运行阶段：
 *      加载 SpringApplication 的运行监听器（SpringApplicationRunListeners）
 *      运行 SpringApplication 的运行监听器（SpringApplicationRunListeners）
 *      监听 SpringBoot 事件、Spring 事件
 *      创建应用上下文（ConfigurableApplicationContext）、Environment（ConfigurableEnvironment） 等
 *          不同的环境会有不同的上下文和 Environment：
 *          Web Reactive： AnnotationConfigReactiveWebServerApplicationContext；
 *          Web Servlet： AnnotationConfigServletWebServerApplicationContext；
 *          非 Web： AnnotationConfigApplicationContext；
 *
 *          Web Reactive： StandardEnvironment；
 *          Web Servlet： StandardServletEnvironment；
 *          非 Web： StandardEnvironment。
 *      回调两个 Runner，非重点（CommandLineRunner, ApplicationRunner）
 *      失败会有故障分析报告
 *
 * 然后关键是注意一个顺序问题，都是使用 Ordered 来控制的。
 *
 * see https://github.com/bfchengnuo/MyRecord/blob/master/%E7%AC%94%E8%AE%B0/Java/Spring%E5%AE%B6%E6%97%8F/SB_depth.md
 */
public class SpringApplicationBootstrap {
    public static void main(String[] args) {
        // run 方法传递的类必须是一个 SB 配置类，例如标注为 @SpringBootApplication 的类
        // SpringApplication.run(ApplicationConfiguration.class, args);

        Set<String> sources = new HashSet<>();
        sources.add(ApplicationConfiguration.class.getName());

        SpringApplication springApplication = new SpringApplication();
        springApplication.setSources(sources);
        springApplication.setWebApplicationType(WebApplicationType.NONE);
        ConfigurableApplicationContext context = springApplication.run(args);

        System.out.println(context.getBean(ApplicationConfiguration.class));

        context.close();
    }

    @SpringBootApplication
    public static class ApplicationConfiguration{}
}
