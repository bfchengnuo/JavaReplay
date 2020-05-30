package com.bfchengnuo.webflux.repository;

import com.bfchengnuo.webflux.entity.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

/**
 * @author 冰封承諾Andy
 * @date 2020/5/28
 */
@Repository
public interface UserRepository extends ReactiveMongoRepository<User, String> {

    /**
     * 根据年龄范围查找
     * @param start 开始
     * @param end 结束
     * @return
     */
    Flux<User> findByAgeBetween(int start, int end);
}
