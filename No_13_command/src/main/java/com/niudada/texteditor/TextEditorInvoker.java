package com.niudada.texteditor;

import java.util.Deque;
import java.util.LinkedList;

public class TextEditorInvoker {
    private Deque<TextCommand> commandHistory = new LinkedList<>();

    public void executeCommand(TextCommand command) {
        command.execute();
        commandHistory.offerLast(command);
    }

    public void undoLastCommand() {
        if (!commandHistory.isEmpty()) {
            TextCommand lastCommand = commandHistory.pollLast();
            lastCommand.undo();
        } else {
            System.out.println("没有可撤销的命令");
        }
    }
}
