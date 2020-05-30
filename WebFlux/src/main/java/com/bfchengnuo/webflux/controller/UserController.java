package com.bfchengnuo.webflux.controller;

import com.bfchengnuo.webflux.entity.User;
import com.bfchengnuo.webflux.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * CRUD 测试
 *
 * @author 冰封承諾Andy
 * @date 2020/5/28
 */
@RestController
@RequestMapping("/user")
public class UserController {
    /**
     * 省略 service 了
     */
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/")
    public Flux<User> getAll() {
        return userRepository.findAll();
    }

    @GetMapping(value = "/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<User> streamAll() {
        return userRepository.findAll();
    }

    @PostMapping("/")
    public Mono<User> createUser(@RequestBody User user) {
        user.setId(null);
        return userRepository.save(user);
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> del(@PathVariable("id") String id) {
        // 删除返回是 void 所以通过查询来完成
        return userRepository.findById(id)
                .flatMap(u -> userRepository.delete(u)
                        .then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK))))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Resource not found")
    @ExceptionHandler(IllegalArgumentException.class)
    public void notFound() {}
}
