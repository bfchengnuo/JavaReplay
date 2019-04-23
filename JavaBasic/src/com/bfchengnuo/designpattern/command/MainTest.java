package com.bfchengnuo.designpattern.command;

/**
 * Created by 冰封承諾Andy on 2017/9/14.
 * 命令模式
 */
public class MainTest {

    public static void main(String[] args) {
        // lightTest();

        MacroCommand macroCommand = new MacroLightCommand();
        Light light = new Light("卧室");
        LovelyLoli loli = new LovelyLoli();
        LightOnCommand lightOnCommand = new LightOnCommand(light);
        LightOffCommand lightOffCommand = new LightOffCommand(light);
        LoliDanceCommand loliDanceCommand = new LoliDanceCommand(loli);

        // 添加到宏命令
        macroCommand.add(lightOnCommand);
        macroCommand.add(loliDanceCommand);
        macroCommand.add(lightOffCommand);

        macroCommand.execute();
        System.out.println("--------------");
        macroCommand.undo();
    }

    private static void lightTest() {
        // 先创建好控制器（请求者）
        RemoteControl remoteControl = new RemoteControl();
        // 创建具体的执行者（接受者）
        Light corridorLight = new Light("走廊");
        Light roomLight = new Light("卧室");

        // 创建相关的具体命令
        LightOnCommand roomOnCmd = new LightOnCommand(roomLight);
        LightOffCommand rommOffCmd = new LightOffCommand(roomLight);
        LightOnCommand corridorOnCmd = new LightOnCommand(corridorLight);
        LightOffCommand corridorOffCmd = new LightOffCommand(corridorLight);

        // 设置到指定的按键上
        remoteControl.setCommand(0,roomOnCmd,rommOffCmd);
        remoteControl.setCommand(1,corridorOnCmd,corridorOffCmd);

        // 调用执行
        remoteControl.onButton(0);
        remoteControl.onButton(1);
        remoteControl.offButton(1);
        // 撤销
        System.out.println("撤销....");
        remoteControl.undoButton();
        remoteControl.offButton(0);
    }
}
