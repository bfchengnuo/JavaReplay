package com.bfchengnuo.java11;

import java.util.ArrayList;

/**
 * @author 冰封承諾Andy
 * @date 2020/5/15
 */
public class VarDemo {
    public static void main(String[] args) {
        // var 只允许在局部变量定义的时候用，Lambda 也可用
        var list = new ArrayList<String>();
        System.out.println(list.getClass().getTypeName());
    }
}
