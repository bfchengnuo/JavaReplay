package com.bfchengnuo.diveinspring.assemble.spring.condition;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

/**
 * Created by 冰封承諾Andy on 2019/5/4.
 */
@Profile("java7")
@Service
public class Java7CalcServiceImpl implements CalcService {
    @Override
    public Integer sum(Integer... values) {
        Integer sum = 0;
        for (Integer value : values) {
            sum += value;
        }
        System.out.println("java7 run...");
        return sum;
    }
}
