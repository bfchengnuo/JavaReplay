package com.bfchengnuo.designpattern.command;

/**
 * Created by 冰封承諾Andy on 2017/9/14.
 * 空命令，默认所有的实现都是空
 * 这样做的好处其实很多，也可以说这就是一种设计模式
 * 有效避免了空指针
 */
public class NoCommand implements Command {
    @Override
    public void execute() {

    }

    @Override
    public void undo() {

    }
}
