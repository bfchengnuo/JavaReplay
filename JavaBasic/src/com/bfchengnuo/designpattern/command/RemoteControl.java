package com.bfchengnuo.designpattern.command;

/**
 * Created by 冰封承諾Andy on 2017/9/14.
 * 来一个比较靠谱的栗子，定义的是遥控器
 * 非常适合的请求者角色
 */
public class RemoteControl {
    private Command[] onCommand;
    private Command[] offCommand;
    // 如果想撤销多次，需要使用一个堆桟进行记录了
    private Command undoCommand; // 撤销命令

    public RemoteControl() {
        // 在构造器中进行初始化，具体几个按键就不用太纠结了
        onCommand = new Command[4];
        offCommand = new Command[4];

        // 默认所有的按键都是空的，这样执行的时候就省了判断 null 了
        NoCommand noCommand = new NoCommand();
        for (int i = 0; i < 4; i++) {
            onCommand[i] = noCommand;
            offCommand[i] = noCommand;
        }

        undoCommand = noCommand;
    }

    public void setCommand(int slot, Command onCommand, Command offCommand) {
        this.onCommand[slot] =onCommand;
        this.offCommand[slot] = offCommand;
    }

    // 打开
    public void onButton(int slot) {
        onCommand[slot].execute();
        // 记录当前的命令
        undoCommand = onCommand[slot];
    }

    // 关闭
    public void offButton(int slot) {
        offCommand[slot].execute();
        // 记录当前的命令
        undoCommand = offCommand[slot];
    }

    public void undoButton() {
        undoCommand.undo();
    }
}
