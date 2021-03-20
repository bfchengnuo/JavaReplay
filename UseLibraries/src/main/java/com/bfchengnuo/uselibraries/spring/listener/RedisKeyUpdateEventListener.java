package com.bfchengnuo.uselibraries.spring.listener;

import cn.hutool.core.util.StrUtil;
import com.bfchengnuo.uselibraries.spring.web.util.WebSocketSessionUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.core.RedisKeyExpiredEvent;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.KeyspaceEventMessageListener;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.Topic;
import org.springframework.lang.Nullable;

/**
 * 监听 Redis 的 key 更新事件
 * 参考： https://github.com/bfchengnuo/JavaReplay/issues/3
 * @see org.springframework.data.redis.listener.KeyExpirationEventMessageListener
 * @author 冰封承諾Andy
 * @date 2021/2/28
 */
// @Component
@Slf4j
public class RedisKeyUpdateEventListener extends KeyspaceEventMessageListener implements ApplicationEventPublisherAware {
    /**
     * 监听0号数据库的 key 事件
     */
    private static final Topic KEYEVENT_UPDATE_TOPIC = new PatternTopic("__keyevent@0__:set");
    @Nullable
    private ApplicationEventPublisher publisher;
    private final RedisTemplate<String,Object> redisTemplate;

    public RedisKeyUpdateEventListener(RedisMessageListenerContainer listenerContainer, RedisTemplate<String,Object> redisTemplate) {
        super(listenerContainer);
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void onMessage(Message message, @Nullable byte[] pattern) {
        String upKey = message.toString();
        if (!upKey.startsWith("msg")) {
            return;
        }

        log.info("redis iu status update, key: {}", upKey);

        if (StrUtil.isBlank(upKey)) {
            return;
        }

        String projectId = upKey.substring(3);
        final String jsonData = String.valueOf(redisTemplate.opsForValue().get(upKey));
        if (StrUtil.isNotBlank(jsonData)) {
            WebSocketSessionUtil.sendStatusMsg(projectId, jsonData);
        }
    }

    @Override
    protected void doRegister(RedisMessageListenerContainer listenerContainer) {
        listenerContainer.addMessageListener(this, KEYEVENT_UPDATE_TOPIC);
    }

    @Override
    protected void doHandleMessage(Message message) {
        this.publishEvent(new RedisKeyExpiredEvent(message.getBody()));
    }

    protected void publishEvent(RedisKeyExpiredEvent event) {
        if (this.publisher != null) {
            this.publisher.publishEvent(event);
        }
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.publisher = applicationEventPublisher;
    }
}
