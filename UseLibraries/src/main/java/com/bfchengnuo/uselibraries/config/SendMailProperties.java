package com.bfchengnuo.uselibraries.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 邮件发送对应的配置 Bean
 * 简单字符串直接使用的 @Value 注解注入，复杂数据结构使用 @ConfigurationProperties
 *
 * @author Created by 冰封承諾Andy on 2019/9/16.
 */
@Component
@Data
@ConfigurationProperties(prefix = "mail")
public class SendMailProperties {
    private String[] toAddress;
}
