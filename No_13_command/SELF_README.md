# 命令模式

命令模式(Command Pattern)是面向对象设计模式中的一种行为型模式,它将请求封装成对象,从而使你可以用不同的请求对客户进行参数化,对请求排队或记录请求日志,以及支持可撤销的操作

## 核心思想

命令模式的核心思想是将"请求"封装成对象,以便使用不同的请求、队列或者日志来参数化其他对象

这种模式将请求的发送者和接收者解耦,使得系统更灵活并可扩展

### 主要角色

1. **命令接口(Command)**：声明执行操作的接口
2. **具体命令(ConcreteCommand)**：实现命令接口的具体命令类
3. **调用者(Invoker)**：请求的发送者,持有命令对象并调用其执行方法
4. **接收者(Receiver)**：真正执行操作的对象
5. **客户端(Client)**：创建具体命令对象并设置其接收者

## 结构与实现

### 基本结构

```java
// 命令接口
public interface Command {
    void execute();
    // 支持撤销操作
    void undo();
}

// 具体命令
public class ConcreteCommand implements Command {
    private Receiver receiver;
    
    public ConcreteCommand(Receiver receiver) {
        this.receiver = receiver;
    }
    
    @Override
    public void execute() {
        receiver.action();
    }
    
    @Override
    public void undo() {
        receiver.undoAction();
    }
}

// 调用者
public class Invoker {
    private Deque<Command> commandHistory = new LinkedList<>();
    
    public void executeCommand(Command command) {
        command.execute();
        commandHistory.offerLast(command);
    }
    
    public void undoLastCommand() {
        if (!commandHistory.isEmpty()) {
            Command lastCommand = commandHistory.pollLast();
            lastCommand.undo();
        }
    }
}

// 接收者
public class Receiver {
    public void action() {
        // 执行具体操作
    }
    
    public void undoAction() {
        // 撤销操作
    }
}
```


### 实现要点

#### 1. 命令历史管理

命令模式中撤销操作的实现依赖于命令历史的管理 通常使用栈结构来存储执行过的命令：

```java
private Deque<Command> commandHistory = new LinkedList<>();

public void executeCommand(Command command) {
    command.execute();
    // 压栈
    commandHistory.offerLast(command);
}

public void undoLastCommand() {
    if (!commandHistory.isEmpty()) {
        // 弹栈
        Command lastCommand = commandHistory.pollLast();
        lastCommand.undo();
    }
}
```


#### 2. 状态保存和恢复

为了支持撤销操作,具体命令类需要保存执行操作前的状态：

```java
public class CancelOrderCommand implements OrderCommand {
    private Order order;
    
    // 保存执行前状态
    private OrderStatus previousStatus;
    
    @Override
    public void execute() {
        // 保存当前状态
        previousStatus = order.getStatus();
        order.setStatus(OrderStatus.CANCELED);
    }
    
    @Override
    public void undo() {
        // 恢复之前状态
        order.setStatus(previousStatus);
    }
}
```


## 优势和适用场景

### 优势

1. **降低系统耦合度**：调用者与接收者之间完全解耦
2. **易于扩展新命令**：符合开闭原则,增加新命令无需修改现有代码
3. **支持撤销操作**：可以方便地实现撤销功能
4. **支持命令队列**：可以将命令存储在队列中,实现批处理
5. **支持宏命令**：可以将多个命令组合成一个复合命令

### 适用场景

1. **GUI菜单和工具栏**：每个菜单项或工具按钮都可以是一个命令对象
2. **多级撤销操作**：如文本编辑器、绘图软件等
3. **事务处理系统**：支持事务的提交和回滚
4. **异步处理**：将请求排队,在不同时间执行
5. **日志系统**：记录操作日志以便系统崩溃后恢复

## Java中实际应用

### 1. java.lang.Runnable

Java中最典型的命令模式应用就是`Runnable`接口：

```java
public interface Runnable {
    public abstract void run();
}

// 使用示例
Thread thread = new Thread(new Runnable() {
    @Override
    public void run() {
        // 具体操作
    }
});
```


### 2. Swing中的Action接口

Swing GUI编程中大量使用命令模式：

```java
Action saveAction = new AbstractAction("Save") {
    public void actionPerformed(ActionEvent e) {
        // 保存操作
    }
};
JButton saveButton = new JButton(saveAction);
```


## 模式变体和扩展

### 1. 支持重做操作

在基本命令模式基础上,可以扩展支持重做(redo)操作：

```java
public interface Command {
    void execute();
    void undo();
    
    // 重做操作
    void redo();
}

public class Invoker {
    private Deque<Command> undoStack = new LinkedList<>();
    private Deque<Command> redoStack = new LinkedList<>();
    
    public void executeCommand(Command command) {
        command.execute();
        undoStack.offerLast(command);

        // 执行新命令时清空重做栈
        redoStack.clear();
    }
    
    public void undoLastCommand() {
        if (!undoStack.isEmpty()) {
            Command command = undoStack.pollLast();
            command.undo();
            redoStack.offerLast(command);
        }
    }
    
    public void redoLastCommand() {
        if (!redoStack.isEmpty()) {
            Command command = redoStack.pollLast();
            command.redo();
            undoStack.offerLast(command);
        }
    }
}
```


### 2. 宏命令

将多个命令组合成一个复合命令：

```java
public class MacroCommand implements Command {
    private List<Command> commands = new ArrayList<>();
    
    public void addCommand(Command command) {
        commands.add(command);
    }
    
    @Override
    public void execute() {
        for (Command command : commands) {
            command.execute();
        }
    }
    
    @Override
    public void undo() {
        // 逆序撤销
        for (int i = commands.size() - 1; i >= 0; i--) {
            commands.get(i).undo();
        }
    }
}
```


## 模式对比与选择

### 与策略模式的区别

- **命令模式**：封装请求,强调操作的执行和撤销
- **策略模式**：封装算法,强调算法的替换和选择

### 与模板方法模式的区别

- **命令模式**：通过对象封装操作,支持运行时动态决定操作
- **模板方法模式**：通过继承定义算法骨架,子类实现具体步骤

## 最后

> 命令模式是一种强大的行为型设计模式,通过将请求封装为对象,实现了请求发送者与接收者的解耦
>
> 它比较适用于需要支持撤销操作、命令队列或复杂事务处理的场景
>
> 但还是那句话：设计模式不是万能滴,主要是理解其适用场景,在合适的地方使用合适的模式