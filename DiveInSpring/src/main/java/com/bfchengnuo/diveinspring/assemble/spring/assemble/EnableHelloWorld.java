package com.bfchengnuo.diveinspring.assemble.spring.assemble;

import org.springframework.context.annotation.Import;

/**
 * Created by 冰封承諾Andy on 2019/5/3.
 *
 * 使用 @Enable 模块装配的两种方式： 注解方式（HelloWorldConfiguration）、编程模式（HelloWorldImportSelector）。
 * 使用编程方式能更加灵活的进行控制。
 */
@Import(HelloWorldImportSelector.class)
// @Import(HelloWorldConfiguration.class)
public @interface EnableHelloWorld {

}

