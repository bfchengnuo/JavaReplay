package com.bfchengnuo.webflux.ext;

import java.lang.annotation.*;

/**
 * 指定调用的服务地址
 *
 * @author 冰封承諾Andy
 * @date 2020/5/28
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MpsApi {
    String value();
}
