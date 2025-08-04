package com.niudada.bank;

public interface BankCommand {
    void execute();
    void undo();
}
