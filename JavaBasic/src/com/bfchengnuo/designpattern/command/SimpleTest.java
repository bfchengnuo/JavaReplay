package com.bfchengnuo.designpattern.command;

/**
 * Created by 冰封承諾Andy on 2017/9/14.
 * 命令模式
 */
public class SimpleTest {
    public static void main(String[] args) {
        // 调用者
        LoliconControl control = new LoliconControl();
        // 具体执行者
        LovelyLoli loli = new LovelyLoli();
        // 创建命令
        LoliDanceCommand command = new LoliDanceCommand(loli);

        // 设置命令
        control.setCommand(command);
        // 调用命令
        control.action();
    }
}
