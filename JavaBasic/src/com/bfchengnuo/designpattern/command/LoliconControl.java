package com.bfchengnuo.designpattern.command;

/**
 * Created by 冰封承諾Andy on 2017/9/14.
 * 请求者角色，也就是负责调用命令的
 */
public class LoliconControl {
    // 首先里面包含有一个命令，这个命令控制着一个“功能”
    private Command command;

    // 提供一个 set 方法可以随时更改命令
    public void setCommand(Command command) {
        this.command = command;
    }

    // 调用命令相关的方法称为行动方法
    public void action(){
        command.execute();
    }
}
