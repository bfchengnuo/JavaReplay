package com.bfchengnuo.designpattern.command;

/**
 * Created by 冰封承諾Andy on 2017/9/14.
 */
public class LightOnCommand implements Command {
    private Light light;

    public LightOnCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.on();
    }

    @Override
    public void undo() {
        light.off();
    }
}
