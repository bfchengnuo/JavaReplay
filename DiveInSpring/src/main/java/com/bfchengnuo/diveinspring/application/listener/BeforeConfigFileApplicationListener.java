package com.bfchengnuo.diveinspring.application.listener;

import org.springframework.boot.context.config.ConfigFileApplicationListener;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.boot.context.event.ApplicationPreparedEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.SmartApplicationListener;
import org.springframework.core.Ordered;

/**
 * @author Created by 冰封承諾Andy on 2019/5/7.
 *
 * 测试用，监听 SpringBoot 事件，在配置文件加载完成后调用；
 * 通过工厂机制加载（spring.factory）
 * 参考实现： {@link ConfigFileApplicationListener}
 * SmartApplicationListener extends ApplicationListener
 *
 * see https://github.com/bfchengnuo/MyRecord/blob/master/%E7%AC%94%E8%AE%B0/Java/Spring%E5%AE%B6%E6%97%8F/SB_depth.md#eventpublishingrunlistener-%E7%9B%91%E5%90%AC%E6%96%B9%E6%B3%95%E4%B8%8E-spring-boot-%E4%BA%8B%E4%BB%B6%E5%AF%B9%E5%BA%94%E5%85%B3%E7%B3%BB
 */
public class BeforeConfigFileApplicationListener implements SmartApplicationListener, Ordered {

    @Override
    public int getOrder() {
        // 调整顺序, 如果在 DEFAULT_ORDER 之前就无法读取，也就是比 DEFAULT_ORDER 小
        // return ConfigFileApplicationListener.DEFAULT_ORDER - 1;
        return ConfigFileApplicationListener.DEFAULT_ORDER + 1;
    }

    @Override
    public boolean supportsEventType(Class<? extends ApplicationEvent> eventType) {
        return ApplicationEnvironmentPreparedEvent.class.isAssignableFrom(eventType)
                || ApplicationPreparedEvent.class.isAssignableFrom(eventType);
    }

    /**
     * 默认为 true 可以不 Override
     */
    @Override
    public boolean supportsSourceType(Class<?> sourceType) {
        return true;
    }

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if (event instanceof ApplicationEnvironmentPreparedEvent) {
            ApplicationEnvironmentPreparedEvent event1 = (ApplicationEnvironmentPreparedEvent) event;
            String name = event1.getEnvironment().getProperty("name");
            System.out.println("BeforeConfigFileApplicationListener##getProperty-name: " + name);
        }
        if (event instanceof ApplicationPreparedEvent) {

        }
    }
}
