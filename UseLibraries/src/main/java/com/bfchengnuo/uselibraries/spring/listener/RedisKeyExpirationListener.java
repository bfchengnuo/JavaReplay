package com.bfchengnuo.uselibraries.spring.listener;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.listener.KeyExpirationEventMessageListener;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;

// @Component
public class RedisKeyExpirationListener extends KeyExpirationEventMessageListener {
 
    public RedisKeyExpirationListener(RedisMessageListenerContainer listenerContainer) {
        super(listenerContainer);
    }
 
    /**
     * 针对redis数据失效事件，进行数据处理
     * @param message message.toString()获取失效的key
     * @param pattern
     */
    @Override
    public void onMessage(Message message, byte[] pattern) {
        String expiredKey = message.toString();
        // 获得key之后可以做自己的逻辑操作

    }
}