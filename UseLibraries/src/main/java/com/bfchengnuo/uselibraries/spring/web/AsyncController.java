package com.bfchengnuo.uselibraries.spring.web;

import com.baomidou.mybatisplus.extension.api.R;
import com.bfchengnuo.uselibraries.spring.async.AsyncExec;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 冰封承諾Andy
 * @date 2021/8/30
 */
@RestController
@AllArgsConstructor
public class AsyncController {
    private final AsyncExec asyncExec;

    @GetMapping("/async/test")
    public R asyncTest() {

        asyncExec.asyncWait();

        asyncExec.asyncCount();
        return R.ok("Done");
    }
}
