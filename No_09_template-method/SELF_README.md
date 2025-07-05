# 模板方法模式学习文档

---

## 一、模板方法模式简介

### 1.1 定义

> 模板方法模式(**Template Method Pattern**)是一种 **行为型设计模式**,它在一个抽象类中定义一个操作的骨架,并将某些步骤的具体实现延迟到子类中;
> 
> 该模式通过父类定义算法流程,子类只负责实现特定步骤,从而保证算法结构的一致性;

### 1.2 核心思想

> 在父类中定义算法的骨架,将某些步骤推迟到子类中实现,子类可以在不改变算法结构的前提下重新定义某些步骤

### 1.3 典型应用场景

- 多个子类有共同的行为逻辑,但具体实现不同
- 算法的整体流程必须保持一致,但部分步骤可以灵活扩展
- 避免重复代码,将公共逻辑集中于父类中

---

## 二、项目中的模板方法模式实现

### 2.1 类图说明

| 类名 | 类型 | 说明 |
|------|------|------|
| [OrderPaymentTemplate](src/main/java/com/niudada/OrderPaymentTemplate.java) | 抽象类 | 定义支付流程骨架和通用方法 |
| [AlipayPayment](src/main/java/com/niudada/AlipayPayment.java) | 具体类 | 实现支付宝支付的具体逻辑 |
| [WeChatPayPayment](src/main/java/com/niudada/WeChatPayPayment.java) | 具体类 | 实现微信支付的具体逻辑 |

---

## 三、代码分析

### 3.1 抽象类：[OrderPaymentTemplate](src/main/java/com/niudada/OrderPaymentTemplate.java)

```java
public abstract class OrderPaymentTemplate {

    // 通用方法
    private void validateOrder() {
        log.info("验证订单信息...");
    }

    private void recordpayment() {
        log.info("记录支付结果...");
    }

    private void sendNotification() {
        log.info("发送支付结果通知...\n");
    }

    // 抽象方法,具体实现延迟到子类
    protected abstract void performPayment();

    // 模板方法,定义支付流程骨架
    public final void processPayment() {
        validateOrder();
        performPayment();
        recordpayment();
        sendNotification();
    }
}
```


#### ✅ 关键点：

- [processPayment()](src/main/java/com/niudada/OrderPaymentTemplate.java) 是模板方法,使用 `final` 修饰防止被子类重写,确保流程不变
- [validateOrder()](src/main/java/com/niudada/OrderPaymentTemplate.java)、[recordpayment()](src/main/java/com/niudada/OrderPaymentTemplate.java) 和 [sendNotification()](src/main/java/com/niudada/OrderPaymentTemplate.java) 是通用步骤,所有子类共享
- [performPayment()](src/main/java/com/niudada/OrderPaymentTemplate.java) 是抽象方法,子类必须实现其具体行为

---

### 3.2 子类实现：[AlipayPayment](src/main/java/com/niudada/AlipayPayment.java) 和 [WeChatPayPayment](src/main/java/com/niudada/WeChatPayPayment.java)

#### [AlipayPayment.java](src/main/java/com/niudada/AlipayPayment.java)

```java
public class AlipayPayment extends OrderPaymentTemplate {
    @Override
    protected void performPayment() {
        log.info("使用支付宝进行支付...");
    }
}
```


#### [WeChatPayPayment.java](src/main/java/com/niudada/WeChatPayPayment.java)

```java
@Slf4j
public class WeChatPayPayment extends OrderPaymentTemplate {
    @Override
    protected void performPayment() {
        log.info("使用微信进行支付...");
    }
}
```


#### ✅ 特点：

- 子类只需关注 [performPayment()](src/main/java/com/niudada/OrderPaymentTemplate.java) 的实现
- 整体流程由父类控制,避免重复代码

---

#### ✅ 主程序输出结果：

```
[INFO] 验证订单信息...
[INFO] 使用支付宝进行支付...
[INFO] 记录支付结果...
[INFO] 发送支付结果通知...

[INFO] 验证订单信息...
[INFO] 使用微信进行支付...
[INFO] 记录支付结果...
[INFO] 发送支付结果通知...
```


---

## 四、模板方法模式的优点

| 优点 | 说明 |
|------|------|
| 🧱 结构清晰 | 父类统一定义流程,子类仅需实现细节 |
| 🔁 可扩展性强 | 新增支付方式只需继承并实现抽象方法 |
| 📦 减少重复代码 | 公共逻辑集中在父类中 |
| 🚫 易于维护 | 修改流程只需修改父类模板方法,不影响子类 |

---

## 五、模板方法模式与钩子方法(可选扩展)

虽然当前项目中没有使用钩子方法,但在复杂的场景中,可以通过钩子方法让子类选择是否参与流程

例如：

```java
protected boolean isNeedRecord() {
    return true; // 默认执行
}
```


在模板方法中根据钩子决定是否执行某一步骤：

```java
public final void processPayment() {
    validateOrder();
    performPayment();
    if (isNeedRecord()) {
        recordpayment();
    }
    sendNotification();
}
```


子类可以选择是否覆盖钩子方法：

```java
@Override
protected boolean isNeedRecord() {
    return false; // 不执行记录
}
```


---

## 六、总结

### 6.1 模板方法模式的核心要素

| 要素 | 内容                                      |
|------|-----------------------------------------|
| 抽象类 | 定义通用方法 + 抽象方法 + 模板方法                    |
| 模板方法 | `final` 方法,封装算法骨架 |
| 抽象方法 | 子类必须实现的步骤                               |
| 钩子方法(可选) | 子类可选实现,用于控制流程分支                         |

### 6.2 场景

- 多种实现方式,但整体流程固定
- 需要复用通用逻辑,避免重复代码
- 希望控制子类扩展边界

---