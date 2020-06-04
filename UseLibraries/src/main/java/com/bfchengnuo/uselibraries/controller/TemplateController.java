package com.bfchengnuo.uselibraries.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

/**
 * 测试模版引擎，目前使用 freemarker
 * 注意不要使用 @RestController
 *
 * @author 冰封承諾Andy
 * @date 2020/6/1
 */
@Controller
public class TemplateController {
    @GetMapping("/index")
    public String staticIndex(Map<String,Object> map) {
        map.put("data", "is Data.");
        return "index";
    }
}
