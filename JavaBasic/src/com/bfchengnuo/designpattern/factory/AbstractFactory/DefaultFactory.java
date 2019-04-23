package com.bfchengnuo.designpattern.factory.AbstractFactory;

/**
 * Created by 冰封承諾Andy on 5/3/2017.
 * 具体工厂角色，可以创建多个对象
 */
public class DefaultFactory extends LoliFactory {
    @Override
    Loli createStan() {
        return new StandardLoli();
    }

    @Override
    Loli createLeg() {
        return new LegitimateLoli();
    }
}
