package com.niudada.order;

import java.util.Deque;
import java.util.LinkedList;

public class OrderInvoker {
    private Deque<OrderCommand> commandHistory = new LinkedList<>();

    public void executeCommand(OrderCommand command) {
        command.execute();
        // 为什么添加到末尾？
        // Java 中的 Deque 是双端队列，既可以当作队列(FIFO)，也可以当作栈(LIFO)使用
        // 命令模式中`撤销`是从后往前一步步回退的，即最后添加的命令最先被撤销，这种更符合栈特性(先进后出、后进先出)
        //  offerLast(command):把命令加到队列末尾(相当于压栈)
        //  pollLast():从队列末尾取出命令(相当于弹栈)
        // 命令模式中撤销的是最近执行的命令，所以要用栈结构，命令加到末尾，撤销也从末尾取
        commandHistory.offerLast(command);
    }

    public void undoLastCommand() {
        if (!commandHistory.isEmpty()) {
            OrderCommand lastCommand = commandHistory.pollLast();
            lastCommand.undo();
        } else {
            System.out.println("没有可撤销的命令");
        }
    }
}
