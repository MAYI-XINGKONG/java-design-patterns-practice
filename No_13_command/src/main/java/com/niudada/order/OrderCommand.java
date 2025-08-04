package com.niudada.order;

public interface OrderCommand {
    void execute();
    void undo();
}
