package com.bfchengnuo.webflux.ext;

import com.bfchengnuo.webflux.entity.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * 返回的 Flux 只要不订阅（subscribe）就不会发生真正的调用，但是会触发代理类
 * 创建者：{@link MyConfig}
 * 测试用例参考：ExtTest
 *
 * @author 冰封承諾Andy
 * @date 2020/5/28
 */
@MpsApi("http://localhost:8080/user")
public interface UserApi {

    @GetMapping("/")
    Flux<User> getAll();

    @GetMapping("/{id}")
    Mono<User> getUserById(@PathVariable("id") String id);

    @PostMapping("/")
    Mono<User> createUser(@RequestBody Mono<User> userMono);
}
