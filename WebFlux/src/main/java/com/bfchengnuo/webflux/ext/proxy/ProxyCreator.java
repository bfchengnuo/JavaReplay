package com.bfchengnuo.webflux.ext.proxy;

/**
 * 创建代理类的接口
 *
 * @author 冰封承諾Andy
 * @date 2020/5/28
 */
public interface ProxyCreator {
    /**
     * 创建代理类
     * @param type 类型
     * @return
     */
    Object createProxy(Class<?> type);
}
