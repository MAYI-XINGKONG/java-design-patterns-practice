### 状态模式学习总结(声明：学习要有自己的思考, 作者也是在学习探索...  有错误大家一起改进)

### 状态模式
比较正式的来讲：状态模式是一种行为型设计模式,它允许对象在其内部状态改变时改变其行为,看起来像是修改了它的类

简单来说,状态模式就是让对象根据自己的状态来决定能做什么

就像一个人,心情好的时候和心情差的时候,对同一件事的反应完全不一样

### 什么时候需要状态模式?
在一些复杂的订单业务中,可能会遇到这中情况：

```java
if (order.status == "未支付") {
    if (action == "支付") {
        // 支付逻辑
    } else if (action == "取消") {
        // 取消逻辑
    } else {
        System.out.println("操作不允许");
    }
} else if (order.status == "已支付") {
    if (action == "发货") {
        // 发货逻辑
    } else if (action == "取消") {
        // 取消逻辑+退款
    } else {
        System.out.println("操作不允许");
    }
}
// ... 还有其他好多状态和嵌套语句
```
这种代码是比较难维护的, 这个时候状态模式就可以用来解决这种问题

### 核心思想
状态模式通过将不同的状态封装成独立的类,将状态相关的操作委托给当前状态对象来处理, 这样可以：
1. 消除复杂的条件分支语句
2. 提高代码的可维护性
3. 使状态转换更加清晰
4. 添加新的状态时更简单

### 本模块Demo：订单状态转换流程
我们通过一个订单状态转换流程来演示状态模式的使用

##### 状态接口

```java
public interface OrderState {
    void payOrder(Order order);     // 支付
    void shipOrder(Order order);    // 发货
    void deliverOrder(Order order); // 收货/已完成
    void cancelOrder(Order order);  // 取消
    String getStateName();          // 获取状态
}
```

##### 未支付状态类
```java
public class UnpaidState implements OrderState {
    @Override
    public void payOrder(Order order) {
        System.out.println("订单已支付,状态从未支付变为已支付");
        order.setState(new PaidState());
    }

    @Override
    public void shipOrder(Order order) {
        System.out.println("订单未支付,无法发货");
    }

    @Override
    public void deliverOrder(Order order) {
        System.out.println("订单未支付,无法收货");
    }

    @Override
    public void cancelOrder(Order order) {
        System.out.println("订单已取消,状态从未支付改为已取消");
        order.setState(new CancelledState());
    }

    @Override
    public String getStateName() {
        return "未支付";
    }
}
```

##### 已支付状态类
```java
public class PaidState implements OrderState {
    @Override
    public void payOrder(Order order) {
        System.out.println("订单已支付,无需重复支付");
    }

    @Override
    public void shipOrder(Order order) {
        System.out.println("订单已发货,状态已支付变为已发货");
        order.setState(new ShippedState());
    }

    @Override
    public void deliverOrder(Order order) {
        System.out.println("订单尚未发货,无法收货");
    }

    @Override
    public void cancelOrder(Order order) {
        System.out.println("订单已取消,状态已支付变为已取消");
        // 一般这种情况还会调用退款API
        System.out.println("已调用退款服务接口完成退款");
        order.setState(new CancelledState());
    }

    @Override
    public String getStateName() {
        return "已支付";
    }
}
```

其他状态类也都类似实现

##### 订单类

```java
public class Order {

    @Getter
    @Setter
    private OrderState state;

    @Getter
    private String orderId;

    public Order(String orderId) {
        this.orderId = orderId;
        // 默认订单状态为未支付
        this.state = new UnpaidState();
        System.out.println("创建订单 " + orderId + "，初始状态为：" + state.getStateName());
    }

    /**
     * 委托给状态对象去处理具体行为
     */
    public void pay() {
        System.out.println("执行支付操作...");
        state.payOrder(this);
    }

    public void ship() {
        System.out.println("执行发货操作...");
        state.shipOrder(this);
    }

    public void deliver() {
        System.out.println("执行收货操作...");
        state.deliverOrder(this);
    }

    public void cancel() {
        System.out.println("执行取消操作...");
        state.cancelOrder(this);
    }

    public void showState() {
        System.out.println("订单 " + orderId + " 当前状态：" + state.getStateName());
    }
}
```
可以自己执行单元测试类查看演示结果

### 优势

1. **消除复杂的条件分支语句**：将状态判断逻辑分散到各个状态类中
2. **提高代码可维护性**：每个状态的行为独立封装,易于理解和修改
3. **符合开闭原则**：添加新状态无需修改现有代码
4. **使状态转换显式化**：状态转换逻辑清晰可见

### 可能适合的场景

状态模式适用于以下情况：
1. 对象的行为依赖于它的状态,并且需要在运行时根据状态去动态的改变行为
2. 代码中包含大量与对象状态相关的条件语句,这些条件语句复杂且难以维护
3. 需要表示对象在不同状态下的不同行为,且状态之间存在复杂的转换关系

### 对比
#### 策略模式 ~vs~ 状态模式
> 策略模式：允许客户端选择不同的算法或策略，这些策略之间是平等的(支付时可以选择不同的支付方式：支付宝、微信、银行卡)

> 状态模式：状态的转换是预定义的，对象的行为随着状态的改变而自动改变

**简单说：[策略模式]是`"我可以选择不同的策略"`,[状态模式]是`"我根据状态自动变化行为"`**

### 注意

1. **状态数量**：状态太多也会导致类的数量急剧增加
2. **状态共享**：对于`无状态的状态对象`,可以考虑使用单例模式
3. **状态持久化**：实际业务中需要考虑状态的持久化存储
4. **并发安全**：多线程环境中需要注意状态转换的原子性

> 无状态的状态对象:
> 
> 首先无状态的状态对象指的是不包含任何实例变量(或只包含常量)的状态类,
> 这类状态对象的行为完全由方法参数决定，不依赖于对象内部存储的数据.
> 这类状态对象天然线程安全,所以可以使用单例

### 总结
状态模式本质上是`用对象代替判断`,把原来需要用条件语句处理的不同状态行为，封装成一个个独立的对象

这个模式特别适合处理业务流程比较复杂的场景,比如订单、工作流等,就应该考虑使用状态模式

但是也要注意状态太多的话会生成很多类

🤔emmmm...  所以还是那句话：具体情况具体分析