package com.bfchengnuo.designpattern.decorator;

/**
 * Created by 冰封承諾Andy on 5/4/2017.
 * 具体装饰(ConcreteDecorator)角色
 * 主要是进行扩展功能
 */
public class LovelyLoli extends Decorator {

    public LovelyLoli(Loli loli) {
        super(loli);
    }

    @Override
    public String speak(String name) {
        return super.speak(name) + " +++装饰+++ 我很可爱~~";
    }

    @Override
    public void hug() {
        // 执行原来的构件的功能
        super.hug();

        // 进行扩展功能
        System.out.println("+++装饰+++ 我很可爱~~");
    }
}
