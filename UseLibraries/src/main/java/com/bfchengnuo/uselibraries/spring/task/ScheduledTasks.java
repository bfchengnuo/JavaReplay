package com.bfchengnuo.uselibraries.spring.task;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 使用 SpringScheduling 完成简单的定时任务
 * 准备条件：在主入口类加入 @EnableScheduling 注解
 *
 * @author Created by 冰封承諾Andy on 2019/9/16.
 */
@Component
@Slf4j
public class ScheduledTasks {

    /**
     * 第一次延迟 1 秒执行，然后每五秒输出，如果需要等待执行完毕后 XX 时间执行，请使用 fixedDelay 属性
     */
    @Scheduled(initialDelay=1000, fixedRate = 5000)
    public void reportCurrentTime() {
        log.debug("现在时间： {}", DateFormatUtils.format(new Date(), "HH:mm:ss"));
    }

    /**
     * 使用 Cron 表达式
     */
    @Scheduled(cron="*/10 * * * * *")
    public void cronPrint() {
        log.debug("这是一个定时任务....");
    }

}
