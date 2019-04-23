package com.bfchengnuo.designpattern.command;

/**
 * Created by 冰封承諾Andy on 2017/9/14.
 *具体命令角色
 */
public class LoliDanceCommand implements Command {
    // 命令角色中会持有一个接受者角色
    private LovelyLoli loli;

    // 使用构造函数来取得
    LoliDanceCommand(LovelyLoli loli) {
        this.loli = loli;
    }

    @Override
    public void execute() {
        System.out.println("准备中.....");
        loli.action();
        System.out.println("谢谢~~");
    }

    @Override
    public void undo() {
        System.out.println("这个，，，没法撤销....");
    }
}
