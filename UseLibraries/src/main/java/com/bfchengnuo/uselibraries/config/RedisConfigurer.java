package com.bfchengnuo.uselibraries.config;

import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;

/**
 * Redis 配置，key 空间通知和事件通知相关
 * @author 冰封承諾Andy
 * @date 2021/2/28
 */
// @Configuration
public class RedisConfigurer extends CachingConfigurerSupport {
    @Bean
    RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        return container;
    }
}