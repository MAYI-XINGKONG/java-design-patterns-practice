# 策略模式学习文档

## 一、什么是策略模式

**策略模式(Strategy Pattern)** 是一种行为型设计模式，它定义了一系列算法或策略，并将每一个算法封装起来，使它们可以互相替换；该模式让算法的变化独立于使用它的客户端

---

## 二、策略模式的核心组成

| 组成                             | 描述                            |
|--------------------------------|-------------------------------|
| **策略接口(Strategy)**             | 定义所有支持的策略的公共操作，通常是某个业务行为的抽象方法 |
| **具体策略类(Concrete Strategies)** | 实现接口，提供不同的算法实现                |
| **引用类(Quote)**                 | 持有一个策略引用，负责与具体的策略进行交互         |

---

## 三、代码详解

### 1. 策略接口 [PaymentStrategy.java](src/main/java/com/niudada/strategy/interfaces/PaymentStrategy.java)

```java
package com.niudada.strategy.interfaces;

public interface PaymentStrategy {
    void pay(int amount);
}
```


- 定义了一个统一的支付行为接口
- 所有支付方式都必须实现这个接口的 [pay](src/main/java/com/niudada/strategy/AliPayStrategy.java) 方法

---

### 2. 具体策略类

#### 微信支付策略 [WeChatPayStrategy](src/main/java/com/niudada/strategy/WeChatPayStrategy.java)

```java
@Slf4j
public class WeChatPayStrategy implements PaymentStrategy {
    @Override
    public void pay(int amount) {
        log.info("叮！微信支付 {} 元", amount / 100.0);
    }
}
```
> 注意：金额单位为`分`，除以 100 转换为`元`显示
> 
> 有疑问？[点击查看SELF_README_QUESTION](SELF_README_QUESTION.md)


#### 支付宝支付策略 [AliPayStrategy](src/main/java/com/niudada/strategy/AliPayStrategy.java)

```java
@Slf4j
public class AliPayStrategy implements PaymentStrategy {
    @Override
    public void pay(int amount) {
        log.info("叮！支付宝支付 {} 元", amount / 100.0);
    }
}
```


#### 银行卡支付策略 [BankCardStrategy](src/main/java/com/niudada/strategy/BankCardStrategy.java)

```java
@Slf4j
public class BankCardStrategy implements PaymentStrategy {
    @Override
    public void pay(int amount) {
        log.info("信息来了！信用卡支付 {} 元", amount / 100.0);
    }
}
```

---

### 3. 引用类 [Payment](src/main/java/com/niudada/pay/Payment.java)

```java
public class Payment {
    private PaymentStrategy strategy;

    public Payment(PaymentStrategy strategy) {
        this.strategy = strategy;
    }

    public void changePaymentStrategy(PaymentStrategy strategy) {
        this.strategy = strategy;
    }

    public void toPay(int amount) {
        strategy.pay(amount);
    }
}
```


- 封装了对策略接口的调用
- 可通过 [changePaymentStrategy()](src/main/java/com/niudada/pay/Payment.java) 动态切换支付方式

---

### 4. 主程序 [Main.java](src/main/java/com/niudada/Main.java)

```java
log.info("你好，总共消费1266元，请扫码或刷卡支付...");
int amount = 126600; // 单位：分

log.info("使用微信支付：");
Payment payment = new Payment(new WeChatPayStrategy());
payment.toPay(100000);

log.info("微信余额不足！使用支付宝支付：");
payment.changePaymentStrategy(new AliPayStrategy());
payment.toPay(20000);

log.info("支付宝余额不足！刷卡支付：");
payment.changePaymentStrategy(new BankCardStrategy());
payment.toPay(6600);

log.info("信息来了！信用卡已冻结！请尽快还款解冻...");
log.info("这是个悲伤的故事😭...");
```


---

## 四、运行结果示例

```
你好，总共消费1266元，请扫码或刷卡支付...
使用微信支付：
叮！微信支付 1000.0 元
微信余额不足！使用支付宝支付：
叮！支付宝支付 200.0 元
支付宝余额不足！刷卡支付：
信息来了！信用卡支付 66.0 元
信息来了！信用卡已冻结！请尽快还款解冻...
这是个悲伤的故事😭...
```


---

## 五、策略模式的优点

| 优点 | 说明 |
|------|------|
| ✅ **可扩展性好** | 新增支付方式只需新增一个类并实现接口即可 |
| ✅ **高内聚低耦合** | 各种支付逻辑彼此隔离，互不影响 |
| ✅ **易于维护和测试** | 每个策略类职责单一，便于单元测试 |
| ✅ **运行时动态切换** | 可在运行时根据用户选择切换不同支付方式 |

---

## 六、适用场景

1. **多种支付方式系统**
2. **不同折扣策略的应用(如满减、打折券)**
3. **日志记录方式的选择(如写文件、发MQ、远程调用)**
4. **数据处理方式的不同(如 CSV、JSON、XML 解析)**

---

## 七、总结

通过本模块的支付 Demo，我们可以看到策略模式的典型应用：

- 接口定义统一行为；
- 不同实现对应不同策略；
- 引用类持有策略引用并执行；
- 客户端无需关心具体实现，只关注接口行为；

这种设计极大地提高了系统的灵活性和可维护性，是实际开发中非常实用的设计模式之一