package com.bfchengnuo.designpattern.command;

/**
 * Created by 冰封承諾Andy on 2017/9/14.
 * 这是一个宏命令，也就是一起执行一组命令
 * 当然这只是抽象的接口....
 */
public interface MacroCommand extends Command {
    // 最基本的添加和删除
    void add(Command command);
    void remove(Command command);
}
