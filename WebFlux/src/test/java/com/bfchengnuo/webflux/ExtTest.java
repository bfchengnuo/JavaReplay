package com.bfchengnuo.webflux;

import com.bfchengnuo.webflux.entity.User;
import com.bfchengnuo.webflux.ext.UserApi;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * 对扩展 {@link com.bfchengnuo.webflux.ext.UserApi} 的测试
 *
 * @author 冰封承諾Andy
 * @date 2020/5/28
 */
@SpringBootTest
public class ExtTest {
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private UserApi userApi;

    @Test
    public void client() {
        Flux<User> userFlux = userApi.getAll();
        // 使用 Flux 进行调用，只要不订阅（subscribe）就不会发生真正的调用，但是会触发代理类
        userFlux.subscribe(System.out::println, e -> System.out.println("调用发送异常：" + e.getMessage()));

        userApi.createUser(
                Mono.just(User.builder()
                        .name("mps")
                        .age(12)
                        .build())
        );
    }
}
