package com.bfchengnuo.designpattern.decorator;

/**
 * Created by 冰封承諾Andy on 5/4/2017.
 * 装饰(Decorator)角色
 * 也是抽象构件类的子类，目的是能够进行多次装饰
 * 作用：引入抽象构件类， 给具体构件类增加职责，但是具体职责在其子类中实现
 */
public class Decorator implements Loli {
    // 持有的构件实例
    private Loli mLoli;

    public Decorator(Loli loli) {
        mLoli = loli;
    }

    @Override
    public String speak(String name) {
        // 具体实现委派给构件实例
        return mLoli.speak(name);
    }

    @Override
    public void hug() {
        // 同上
        mLoli.hug();
    }
}
