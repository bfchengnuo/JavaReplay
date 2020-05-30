package com.bfchengnuo.webflux;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import org.springframework.web.reactive.config.EnableWebFlux;

/**
 * 因为 webFlux 与传统 Servlet 不能共存（很麻烦），所以单独开一个项目展示新趋势；
 * 关于异步 Servlet 的示例参考 DiveInSpring 项目
 *
 * 关于 Netty-WebSocket 的例子参考 {@link com.bfchengnuo.webflux.netty.Run}
 */
@SpringBootApplication
@EnableReactiveMongoRepositories
public class WebfluxApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebfluxApplication.class, args);
    }

}
