package com.bfchengnuo.designpattern.command;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 冰封承諾Andy on 2017/9/14.
 * 具体的宏命令
 */
public class MacroLightCommand implements MacroCommand {
    private List<Command> commands = new ArrayList<>();
    private List<Command> undoCommands = new ArrayList<>();

    @Override
    public void add(Command command) {
        commands.add(command);
    }

    @Override
    public void remove(Command command) {
        commands.remove(command);
    }

    @Override
    public void execute() {
        for (Command command : commands) {
            command.execute();
            undoCommands.add(command);
        }
    }

    @Override
    public void undo() {
        for (int i = undoCommands.size() - 1; i >= 0; i--) {
            undoCommands.get(i).undo();
        }
        undoCommands.clear();
    }
}
