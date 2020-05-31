package com.bfchengnuo.webflux.ext.handle;

import com.bfchengnuo.webflux.ext.beans.MethodInfo;
import com.bfchengnuo.webflux.ext.beans.ServerInfo;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * 执行 Rest 请求的简单实现，可以使用 {@link org.springframework.web.client.RestTemplate}
 * 目前使用 {@link org.springframework.web.reactive.function.client.WebClient} 实现
 *
 * @author 冰封承諾Andy
 * @date 2020/5/28
 */
public class SimpleClientRestHandleImpl implements RestHandle {
    private WebClient webClient;

    @Override
    public void init(ServerInfo serverInfo) {
        this.webClient = WebClient.create(serverInfo.getUrl());
    }

    @Override
    public Object invokeRest(MethodInfo methodInfo) {
        // 构建请求
        WebClient.RequestBodySpec reqSpec = this.webClient
                .method(methodInfo.getMethod())
                .uri(methodInfo.getUrl())
                .accept(MediaType.APPLICATION_JSON);

        WebClient.ResponseSpec rspSpec;
        if (methodInfo.getBody() != null) {
            rspSpec = reqSpec.body(methodInfo.getBody(), methodInfo.getBodyType()).retrieve();
        }else {
            // 发送请求
            rspSpec = reqSpec.retrieve();
        }

        // 处理异常
        rspSpec.onStatus(status -> status == HttpStatus.NOT_FOUND,
                rsp -> Mono.just(new RuntimeException("404 Not Found")));

        // 处理 body
        if (methodInfo.isReturnMono()) {
            return rspSpec.bodyToMono(methodInfo.getReturnType());
        }
        return rspSpec.bodyToFlux(methodInfo.getReturnType());
    }
}
