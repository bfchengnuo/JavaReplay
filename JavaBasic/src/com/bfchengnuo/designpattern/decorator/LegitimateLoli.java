package com.bfchengnuo.designpattern.decorator;

/**
 * Created by 冰封承諾Andy on 5/3/2017.
 * 具体构件(ConcreteComponent)角色
 * 实现接口，具体将要被装饰
 */
public class LegitimateLoli implements Loli {
    @Override
    public String speak(String name) {
        return "(￣^￣)" + name;
    }

    @Override
    public void hug() {
        System.out.println("(づ｡◕‿‿◕｡)づ");
    }
}
