package com.bfchengnuo.uselibraries;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 异常打印测试
 *
 * @author 冰封承諾Andy
 * @date 2020/6/8
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class ExceptionTest {
    @Test
    public void print() {
        try {
            int i = 1 / 0;
        } catch (Exception e) {
            log.error("{} 发生异常：{}", "error", e.getMessage());
            log.error("{} 发生异常：{}", "error", e.getLocalizedMessage());
            log.error("{} 发生异常：{}", "error", e.getStackTrace());
            log.error("{} 发生异常：", "error", e);
        }
    }
}
