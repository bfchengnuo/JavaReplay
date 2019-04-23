package com.bfchengnuo.designpattern.command;

/**
 * Created by 冰封承諾Andy on 2017/9/14.
 * 命令模式---命令角色
 */
public interface Command {
    void execute();

    void undo();  // 撤销的方法
}
