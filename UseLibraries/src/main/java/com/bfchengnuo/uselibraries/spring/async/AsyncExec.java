package com.bfchengnuo.uselibraries.spring.async;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * 简单的异步 @Async 示例
 * 启动类开启异步支持：@EnableAsync，然后在需要异步的方法上标注 @Async，方法返回值需要 void 或者 {@link java.util.concurrent.Future}
 * @author 冰封承諾Andy
 * @date 2021/8/30
 */
@Service
@Slf4j
public class AsyncExec {

    @Async
    public void asyncWait() {
        log.info("进入 asyncWait 方法，等待。。。");

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        log.info("等待完成！");
    }

    @Async
    public void asyncCount() {
        log.info("15s 计数开始。");

        for (int i = 0; i < 15; i++) {
            // log.info("No." + i);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        log.info("计数完成。");
    }
}
