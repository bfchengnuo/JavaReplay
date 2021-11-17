package com.bfchengnuo.uselibraries.spring.async;

import org.springframework.context.annotation.Bean;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * 自定义 @Async 的线程池配置
 * PS：工作中遇到连续调用 @Async 的方法会同步等待执行，暂不清楚原因，但是通过自定义的方式可以解决
 * @author 冰封承諾Andy
 * @date 2021/9/12
 * see https://www.cnblogs.com/hsug/p/13303018.html
 */
public class AsyncConfig {
    @Bean
    public AsyncTaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setThreadNamePrefix("Anno-Executor");
        executor.setMaxPoolSize(10);

        // 设置拒绝策略
        executor.setRejectedExecutionHandler((r, executor1) -> {
            // .....
        });
        // 使用预定义的异常处理类
        // executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());

        return executor;
    }
}
