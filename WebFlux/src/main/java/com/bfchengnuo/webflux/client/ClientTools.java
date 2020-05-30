package com.bfchengnuo.webflux.client;

import com.bfchengnuo.webflux.entity.User;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.reactive.function.BodyExtractors;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.client.ReactorNettyWebSocketClient;
import org.springframework.web.reactive.socket.client.WebSocketClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.time.Duration;
import java.util.Objects;

/**
 * WebFlux 提供了全套的（HTTP、SSE 和 WebSocket）客户端支持
 * 对应的测试类： ClientTest
 *
 * @author 冰封承諾Andy
 * @date 2020/5/28
 */
public class ClientTools {
    /**
     * HTTP Client
     */
    public void http() {
        final User user = new User();
        user.setName("Test");
        user.setAge(22);
        final WebClient client = WebClient.create("http://localhost:8080/test");
        final Mono<User> createdUser = client.post()
                .uri("")
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(user), User.class)
                .exchange()
                .flatMap(response -> response.bodyToMono(User.class));
        System.out.println(createdUser.block());
    }

    /**
     * SSE Client
     */
    public void sse() {
        final WebClient client = WebClient.create();
        client.get()
                .uri("http://localhost:8080/randomNumbers")
                .accept(MediaType.TEXT_EVENT_STREAM)
                .exchange()
                .flatMapMany(response -> response.body(BodyExtractors.toFlux(new ParameterizedTypeReference<ServerSentEvent<String>>() {
                })))
                .filter(sse -> Objects.nonNull(sse.data()))
                .map(ServerSentEvent::data)
                .buffer(10)
                .doOnNext(System.out::println)
                .blockFirst();
    }

    /**
     * WebSocket Client
     */
    public void webSocket() {
        final WebSocketClient client = new ReactorNettyWebSocketClient();
        client.execute(URI.create("ws://localhost:8080/echo"), session ->
                session.send(Flux.just(session.textMessage("Hello")))
                        .thenMany(session.receive().take(1).map(WebSocketMessage::getPayloadAsText))
                        .doOnNext(System.out::println)
                        .then())
                .block(Duration.ofMillis(5000));
    }


}
