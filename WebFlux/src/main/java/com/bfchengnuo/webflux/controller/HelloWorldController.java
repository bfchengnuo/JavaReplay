package com.bfchengnuo.webflux.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuples;

import java.time.Duration;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @author 冰封承諾Andy
 * @date 2020/5/27
 */
@RestController
@Slf4j
public class HelloWorldController {

    @GetMapping("/old")
    public String oldHello() {
        return "old Hello!";
    }

    @GetMapping("/new")
    public Mono<String> newHello() {
        return Mono.just("new Hello");
    }

    @GetMapping("/time")
    public Mono<String> fluxTime() {
        log.debug("start...");
        // 不会阻塞
        Mono<String> result = Mono.fromSupplier(() -> {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.debug("done.");
            return "Time Done.";
        });
        log.debug("end...");
        return result;
    }

    /**
     * 使用 HTML5 的 SSE(Server Send Events) 返回数据
     * https://developer.mozilla.org/zh-CN/docs/Server-sent_events
     */
    @GetMapping(value = "/flux", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> flux() {
        // flux 0-N 个元素
        Flux<String> stringFlux = Flux.fromStream(IntStream.rangeClosed(1, 5).mapToObj(i -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 规范来说要加两个换行
            return "flux data " + i + "\n\n";
        }));

        return stringFlux;
    }

    /**
     * 另一种 SSE 示例
     * @return 返回值是 ServerSentEvent 时会自动转换为 SSE 规范
     */
    @GetMapping("/randomNumbers")
    public Flux<ServerSentEvent<Integer>> randomNumbers() {
        // Duration J8 新增表示时间范围对象
        return Flux.interval(Duration.ofSeconds(1))
                .map(seq -> Tuples.of(seq, ThreadLocalRandom.current().nextInt()))
                .map(data -> ServerSentEvent.<Integer>builder()
                        .event("random")  // SSE Event Name
                        .id(Long.toString(data.getT1()))
                        .data(data.getT2())
                        .build());
    }
}
