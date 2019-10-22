package com.bfchengnuo.uselibraries;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 开启定时任务 @EnableScheduling
 */
@SpringBootApplication
@RestController
public class UseLibrariesApplication {

    public static void main(String[] args) {
        SpringApplication.run(UseLibrariesApplication.class, args);
    }

    @GetMapping("/test")
    public String hello() {
        return "Hello World";
    }
}
