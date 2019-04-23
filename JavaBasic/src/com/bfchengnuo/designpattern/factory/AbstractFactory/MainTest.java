package com.bfchengnuo.designpattern.factory.AbstractFactory;

/**
 * Created by 冰封承諾Andy on 5/3/2017.
 * 测试用类
 */
public class MainTest {
    public static void main(String[] args) {
        LoliFactory factory = new DefaultFactory();
        Loli stanLoli = factory.createStan();
        stanLoli.hug();

        Loli legLoli = factory.createLeg();
        legLoli.hug();
    }
}
