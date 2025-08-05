> 在软件设计中,对象之间的复杂交互往往会导致难以维护和扩展
>
> 尤其是多个对象相互依赖、形成网状结构的系统,这时就有必要引入中介者模式(Mediator Pattern)了

### 定义

中介者模式是一种`行为型`设计模式,它通过引入一个`中介者对象`来`封装`一系列对象之间的`交互关系`,从而将原本复杂的多对多关系转换为简单的多对一关系

这样做的好处是显著降低了系统的耦合度,使得对象之间的交互更加清晰、可控(坏处后面说)

### 核心思想

当前演示模块中,中介者模式包含以下几个核心角色：

1. **[ChatRoomMediator](src/main/java/com/niudada/chatpro/ChatRoomMediator.java)(中介者接口)**：定义了多个对象之间交互的接口
2. **[ChatRoom](src/main/java/com/niudada/chatpro/ChatRoom.java)(具体中介者)**：实现了中介者接口,协调各个对象的交互
3. **[ChatUser](src/main/java/com/niudada/chatpro/ChatUser.java)(具体用户类)**：每个类都知道中介者对象,通过中介者与其他用户进行交互

### 聊天室

当前模块中举的一个简单的例子——聊天室

#### 第一个版本最简单实现

首先,我们定义了中介者接口：

```java
public interface ChatRoomMediator {
    void sendMessage(User user, String message);
    void addUser(User user);
}
```


然后是具体的中介者实现：

```java
public class ChatRoom implements ChatRoomMediator {
    private List<User> users;

    public ChatRoom() {
        this.users = new ArrayList<>();
    }

    @Override
    public void sendMessage(User user, String message) {
        for (User u : users) {
            u.receive(user.getName(), message);
        }
    }

    @Override
    public void addUser(User user) {
        users.add(user);
    }
}
```


用户类的定义：

```java
public abstract class User {
    protected ChatRoomMediator mediator;
    protected String name;

    public User(ChatRoomMediator mediator, String name) {
        this.mediator = mediator;
        this.name = name;
    }

    public abstract void send(String message);
    public abstract void receive(String senderName, String message);
}
```


具体用户类实现：

```java
public class ChatUser extends User {
    public ChatUser(ChatRoomMediator mediator, String name) {
        super(mediator, name);
    }

    @Override
    public void send(String message) {
        System.out.println(this.name + " 发送消息:\" " + message + "\"");
        mediator.sendMessage(this, message);
    }

    @Override
    public void receive(String senderName, String message) {
        System.out.println(this.name + " 收到[ " + senderName + " ]的消息: \"" + message + "\"");
    }
}
```


#### 图形界面增强版

有聊天界面的版本,虽然很low,不过还是更直观一点：

```java
public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        // 创建聊天室中介者
        ChatRoom chatRoom = new ChatRoom();

        // 创建用户
        ChatUser user1 = new ChatUser(chatRoom, "用户1");
        ChatUser user2 = new ChatUser(chatRoom, "用户2");

        // 创建窗口
        ChatWindow window1 = new ChatWindow(user1);
        ChatWindow window2 = new ChatWindow(user2);

        // 添加用户到聊天室
        chatRoom.addUser(user1);
        chatRoom.addUser(user2);

        // 显示所有窗口
        window1.show();
        window2.show();
    }
}
```

### 优势

- 降低耦合度
  - 在没有中介者的情况下,每个用户都需要直接引用其他用户,形成复杂的网状结构; 而通过中介者模式,所有用户只与中介者交互,大大降低了耦合度
- 提高可维护性
  - 当需要修改交互逻辑时,我们只需要修改中介者类,而不需要改动各个用户类; 这遵循了"开闭原则",让系统更易维护
- 提高扩展性
  - 添加新的用户时非常简单,只要实现相应的接口并注册到中介者中就可以,不用修改现有代码
- 复杂逻辑集中
  - 将复杂的交互逻辑集中在中介者中,使逻辑更加清晰,便于理解和维护

### 适用场景

中介者模式适用于以下场景：
1. 当系统中对象之间存在复杂的引用关系,相互依赖,形成网状结构,难以维护和扩展
2. 想定制一个分布在多个类中的行为,但又不想生成太多的子类

还有一些常见的场景也有：
- 聊天室
- MVC架构中的Controller
- GUI组件交互协调

### 有优势就有弱点

虽然中介者模式有很多优势,但在使用时也要注意：

#### 要避免中介者过于庞大
> 中介者类可能会变得非常复杂和庞大,需要合理划分职责,避免出现"上帝对象"
> 
> 就像一个人知道的太多也不好,容易被灭口🤪

#### 性能问题
> 所有交互都通过中介者的话,当交互逐渐增多,那中介者内部的逻辑可能会成为性能瓶颈,这个需要注意


**就是说中介者不要过于复杂,需要在降低耦合度和保持简洁性之间找到平衡**

### 和其他模式的区别

`观察者模式`是一对多的依赖关系,就像一个被关注的公众号(被观察者)有很多读者(观察者),当有新内容时会通知所有读者

`中介者模式`是多对一的协调关系,像租房一样,有多个房东和租客通过一个中介者协调沟通

简而言之: 观察者模式是`一对多通知`,中介者模式是`多对多协调`

### 总结一下

中介者模式很好用,所以经常会被滥用,还是那句话,根据实际情况考虑吧