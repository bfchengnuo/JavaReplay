package com.bfchengnuo.diveinspring.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 冰封承諾Andy
 * @date 2020/5/29
 */
@RestController
public class TestController {
    @GetMapping("/hello")
    public String hello() {
        return "Hello.";
    }
}
