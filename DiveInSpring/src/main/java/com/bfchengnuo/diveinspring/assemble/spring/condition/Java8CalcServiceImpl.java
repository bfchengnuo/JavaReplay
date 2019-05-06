package com.bfchengnuo.diveinspring.assemble.spring.condition;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

/**
 * Created by 冰封承諾Andy on 2019/5/4.
 */
@Profile("java8")
@Service
public class Java8CalcServiceImpl implements CalcService {
    @Override
    public Integer sum(Integer... values) {
        System.out.println("java8 run...");
        return Stream.of(values).reduce(0, Integer::sum);
    }
}
