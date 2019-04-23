package com.bfchengnuo.designpattern.template;

/**
 * Created by 冰封承諾Andy on 2017/9/21.
 * 模板方法
 */
public class MainTest {
    public static void main(String[] args) {
        AbstractTemplate template = new ConcreteTemplate();
        template.prepareExecute();
        System.out.println("-----------------");
        template.prepareExecute2();
    }
}
