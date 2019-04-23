package com.bfchengnuo.designpattern.template;

/**
 * Created by 冰封承諾Andy on 2017/9/21.
 * 抽象模板角色
 */
public abstract class AbstractTemplate {

    // 模板方法
    // 使用 final 关键字保证算法不会被篡改
    public final void prepareExecute() {
        // 调用基本方法
        Operation1();
        Operation2();
        hookMethod();
        Operation3();
    }

    // 模板方法示例2，使用钩子控制
    public final void prepareExecute2() {
        // 调用基本方法
        Operation1();
        Operation2();
        if (hookMethod2()) {
            System.out.println("钩子控制的代码被执行....");
            Operation3();
        }
    }

    boolean hookMethod2() {
        return false; // 默认为 false
    }

    // 基本方法声明（由子类实现）
    protected abstract void Operation1();

    private void Operation2() {
        System.out.println("顶级类中的方法执行....");
    }

    private final void Operation3() {
        System.out.println("顶级类方法 final");
    }

    // 基本方法，空方法--钩子
    void hookMethod() {

    }
}
