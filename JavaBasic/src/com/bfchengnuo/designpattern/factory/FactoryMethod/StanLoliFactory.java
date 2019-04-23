package com.bfchengnuo.designpattern.factory.FactoryMethod;

/**
 * Created by 冰封承諾Andy on 5/3/2017.
 * 具体工厂角色 No.1
 */
public class StanLoliFactory extends LoliFactory {

    @Override
    Loli create() {
        return new StandardLoli();
    }
}
