package com.bfchengnuo.uselibraries.spring;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Locale;

/**
 * Spring 中静态工具类中注入 Spring IoC 对象示例
 * 首先要使用 @Component 来被 Spring 管理，此时里面的静态变量都是 NULL，
 * 通过使用 @PostConstruct 和一个临时变量给静态变量赋值，
 * 这样，就可以直接拿着类名调用静态方法，使用 IoC 的 Bean
 *
 * @author 冰封承諾Andy
 * @date 2021/1/30
 */
@Component
@RequiredArgsConstructor
public class StaticAutowired {
    /**
     * 用来注入使用
     */
    private final MessageSource messageSource;
    private static MessageSource staticMessageSource;

    public static String get(String msgKey) {
        try {
            return staticMessageSource.getMessage(msgKey, null, getLocale());
        } catch (Exception e) {
            return msgKey;
        }

    }

    private static Locale getLocale() {
        final String language = "";
        if (Locale.US.toString().equals(language)) {
            return Locale.US;
        }
        return Locale.CHINA;
    }

    @PostConstruct
    public void init() {
        staticMessageSource = messageSource;
    }
}
