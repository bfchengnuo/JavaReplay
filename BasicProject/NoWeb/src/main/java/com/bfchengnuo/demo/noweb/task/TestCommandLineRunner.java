package com.bfchengnuo.demo.noweb.task;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * CommandLineRunner 在 SB 启动完成后执行
 * MP 多数据源：https://baomidou.com/guide/dynamic-datasource.html
 * @author 冰封承諾Andy
 * @date 2021/11/17
 */
@Component
public class TestCommandLineRunner implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        System.out.println("run....");
    }
}
