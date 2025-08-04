package com.niudada.bank;

import java.util.Deque;
import java.util.LinkedList;

public class BankInvoker {
    private Deque<BankCommand> commandHistory = new LinkedList<>();

    public void executeCommand(BankCommand command) {
        command.execute();
        commandHistory.offerLast(command);
    }

    public void undoLastCommand() {
        if (!commandHistory.isEmpty()) {
            BankCommand lastCommand = commandHistory.pollLast();
            lastCommand.undo();
        } else {
            System.out.println("没有可撤销的命令");
        }
    }
}
