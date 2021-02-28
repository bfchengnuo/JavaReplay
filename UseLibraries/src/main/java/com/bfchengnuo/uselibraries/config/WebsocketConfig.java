package com.bfchengnuo.uselibraries.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @author 冰封承諾Andy
 * @date 2021/2/28
 */
@Configuration
public class WebsocketConfig {
    /**
     * 内置容器必须创建，外部容器由容器提供，可以不创建
     * 这个 bean 会自动注册使用了 @ServerEndpoint 注解声明的 Websocket endpoint
     * @return /
     */
    @Bean
    public ServerEndpointExporter serverEndpointExporter(){
        return new ServerEndpointExporter();
    }
}
