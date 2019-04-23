package com.bfchengnuo.designpattern.factory.FactoryMethod;

/**
 * Created by 冰封承諾Andy on 5/3/2017.
 * 用来调用测试
 */
public class MainTest {
    public static void main(String[] args) {
        LoliFactory factory = new LegLoliFactory();
        Loli legLoli = factory.create();
        legLoli.hug();

        System.out.println("-------------------------");

        factory = new StanLoliFactory();
        Loli stanLoli = factory.create();
        stanLoli.hug();
    }
}
