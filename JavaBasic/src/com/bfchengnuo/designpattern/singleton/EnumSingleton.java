package com.bfchengnuo.designpattern.singleton;

/**
 * Created by 冰封承諾Andy on 2019/4/21.
 * 枚举方式实现单例
 *
 * PS： 枚举其实是一个抽象类，它打破了抽象类中不能有 final 方法的规定（隐式）
 */
public enum EnumSingleton {
    /**
     * 默认是私有构造，每一个枚举类型都是一个自身的实例对象
     */
    INSTANCE
}
