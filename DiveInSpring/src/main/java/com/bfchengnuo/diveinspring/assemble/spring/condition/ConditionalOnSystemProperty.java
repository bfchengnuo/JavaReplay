package com.bfchengnuo.diveinspring.assemble.spring.condition;

import org.springframework.context.annotation.Conditional;

import java.lang.annotation.*;

/**
 * Created by 冰封承諾Andy on 2019/5/4.
 *
 * 根据系统属性来进行条件判断
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Conditional(OnSystemPropertyCondition.class)
public @interface ConditionalOnSystemProperty {
    /**
     * 系统属性的名字
     * @return
     */
    String name();

    /**
     * 系统属性的值
     * @return
     */
    String val();
}
