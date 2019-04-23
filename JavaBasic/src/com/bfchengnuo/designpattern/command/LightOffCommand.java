package com.bfchengnuo.designpattern.command;

/**
 * Created by 冰封承諾Andy on 2017/9/14.
 */
public class LightOffCommand implements Command {
    private Light light;

    public LightOffCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        // 这里其实可以执行一系列的方法
        // 如果次对象的状态存在多种，可以在执行之前记录当前的状态；以便给后面的撤销操作用
        light.off();
    }

    @Override
    public void undo() {
        // 如果存在多个状态，在 exec 中记录状态，在这里获取记录的状态，然后再相应的处理
        light.on();
    }
}
