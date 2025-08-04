##### 定义
责任链模式(Chain of Responsibility Pattern)是面向对象设计中的一种经典行为模式,它为请求的发送者和接收者之间提供了解耦机制

> 通过将多个处理对象串联成一条链,请求沿着这条链传递,直到有对象处理它为止

### 1 核心思想

责任链模式的核心思想是：将请求沿着处理者链进行传递,让多个对象都有机会处理这个请求

这些处理者可以动态地添加或删除,从而实现处理逻辑的灵活配置

在责任链模式中：
- 每个处理者都持有对下一个处理者的引用
- 处理者决定是否处理请求以及是否将请求传递给下一个处理者
- 客户端只需将请求发送给链中的第一个处理者

### 2. 相关结构
##### Handler(处理者)
定义一个处理请求的接口,通常包含一个指向下一个处理者的引用

##### ConcreteHandler(具体处理者)
实现处理请求的具体逻辑,如果自己不能处理,则将请求转发给下一个处理者

##### Client(客户端)
创建处理链并向链中的第一个处理者发送请求

### 8. 与其他模式的关系

> 组合模式：责任链模式经常与组合模式一起使用,形成树状的责任链结构
>
> 装饰器模式：虽然两种模式都涉及对象链,但装饰器模式主要目的是增加功能,而责任链模式的目的是处理请求
>
> 状态模式：状态模式中状态的转换是预定义的,而责任链模式中处理者的顺序可以动态调整

### 4. 方式和场景

#### 4.1 处理方式

责任链模式有两种常见的处理方式：

1. **单处理者模式**：一旦某个处理者处理了请求,链就终止
2. **多处理者模式**：多个处理者可以依次处理同一个请求

#### 4.2 场景

责任链模式适用于以下情况：

- 有多个对象可以处理同一个请求,具体哪个对象处理该请求在运行时自动确定
- 想向多个对象中的一个提交请求,但不明确指定接收者
- 处理请求的对象集合应该被动态指定

### 5. Demo

#### 5.1 数据验证

在用户数据验证场景中,我们需要按顺序验证用户名、密码、邮箱和年龄等字段

每个验证器只关注自己的验证逻辑,如果验证失败则终止链,验证成功则传递给下一个验证器

实现要点：
- [Validator](src/main/java/com/niudada/data_validate/Validator.java) 作为抽象基类定义了链式结构
- 每个具体验证器([UsernameValidator](src/main/java/com/niudada/data_validate/UsernameValidator.java)、[PasswordValidator](src/main/java/com/niudada/data_validate/PasswordValidator.java)等)实现自己的验证逻辑
- 验证失败时立即返回,不继续传递

#### 5.2 请求过滤

在Web请求处理中,请求需要经过日志记录、身份验证、权限检查、安全检测等多个步骤

每个过滤器负责一个特定功能,根据处理结果决定是否继续传递请求

实现要点：
- [RequestFilter](src/main/java/com/niudada/request_filter/RequestFilter.java) 定义了过滤器的基本结构
- 各个具体过滤器([LoggingFilter](src/main/java/com/niudada/request_filter/LoggingFilter.java)、[AuthenticationFilter](src/main/java/com/niudada/request_filter/AuthenticationFilter.java)等)实现各自的处理逻辑
- 根据处理结果决定是否继续传递请求

> 结构都差不多,无非是场景和实现不同

### 6. 该模式优缺点

#### 6.1 优点

1. **降低耦合度**：请求发送者和接收者之间解耦
2. **增强灵活性**：可以动态增加或修改处理链
3. **符合开闭原则**：增加新的处理者无需修改现有代码
4. **简化对象**：每个处理者只需关注自己能处理的请求

#### 6.2 缺点

1. **不能保证请求一定被处理**：可能没有相应的处理者
2. **性能问题**：请求可能需要遍历整个链
3. **调试困难**：链较长时难以调试和排查问题

##### 所以说：
- 合理设计链的长度,过长的处理链会影响性能
- 为链提供默认处理者,确保请求一直能被处理
- 每个处理者应该明确自己的处理逻辑和是否继续传递的条件

### 7. Java中应用

#### 7.1 Servlet过滤器

Java Servlet规范中的`Filter`接口是责任链模式的典型应用：

```java
public interface Filter {
    void doFilter(ServletRequest request, ServletResponse response, FilterChain chain);
}
```


#### 7.2 日志系统

Java内置的日志系统`java.util.logging.Logger`也使用了责任链模式：

```java
logger.log(Level.INFO, "Log message");
```