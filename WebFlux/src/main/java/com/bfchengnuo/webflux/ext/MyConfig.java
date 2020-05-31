package com.bfchengnuo.webflux.ext;

import com.bfchengnuo.webflux.ext.proxy.JDKProxyCreatorImpl;
import com.bfchengnuo.webflux.ext.proxy.ProxyCreator;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 基于 Spring#FactoryBean 和 JDK 动态代理的扩展实现
 * 读取注解信息实现 OpenFeign 的类似功能
 *
 * @author 冰封承諾Andy
 * @date 2020/5/28
 */
// @Configuration
public class MyConfig {

    /**
     * 通过 FactoryBean 创建 {@link UserApi} 的实例对象（代理对象）
     * @param proxyCreator
     * @return
     */
    @Bean
    private FactoryBean<UserApi> mpsApiConfig(ProxyCreator proxyCreator) {
        return new FactoryBean<UserApi>() {
            /**
             * 返回代理对象
             */
            @Override
            public UserApi getObject() throws Exception {
                return (UserApi) proxyCreator.createProxy(this.getObjectType());
            }

            @Override
            public Class<?> getObjectType() {
                // 得到对象类型
                return UserApi.class;
            }
        };
    }

    @Bean
    private ProxyCreator jdkProxyCreator() {
        return new JDKProxyCreatorImpl();
    }
}
