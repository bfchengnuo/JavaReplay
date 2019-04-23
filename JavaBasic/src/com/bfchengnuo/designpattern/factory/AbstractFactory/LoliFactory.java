package com.bfchengnuo.designpattern.factory.AbstractFactory;

/**
 * Created by 冰封承諾Andy on 5/3/2017.
 * 抽象工厂角色，提供工厂的公共方法
 */
abstract class LoliFactory {
    abstract Loli createStan();
    abstract Loli createLeg();
}
