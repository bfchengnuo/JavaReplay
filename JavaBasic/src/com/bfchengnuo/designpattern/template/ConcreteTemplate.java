package com.bfchengnuo.designpattern.template;

/**
 * Created by 冰封承諾Andy on 2017/9/21.
 * 具体模板角色
 */
public class ConcreteTemplate extends AbstractTemplate {
    @Override
    protected void Operation1() {
        System.out.println("子类中的具体实现");
    }

    @Override
    void hookMethod() {
        System.out.println("使用钩子....");
    }

    @Override
    boolean hookMethod2() {
        return true;
    }
}
