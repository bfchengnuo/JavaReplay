package com.bfchengnuo.webflux;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;

@SpringBootTest
class WebfluxApplicationTests {

    @Test
    void fluxTest() {
        String[] strs = {"1", "2", "3"};

        // 使用类似 JDK8 的 Stream
        Flux.fromArray(strs)
                .map(Integer::parseInt)
                // 最终操作，类似 JDK9 的 Reactive Stream
                .subscribe(System.out::println);
    }

}
