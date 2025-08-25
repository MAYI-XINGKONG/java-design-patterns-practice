### 桥接模式:解耦抽象与实现

桥接模式(Bridge Pattern)作为Gang of Four设计模式之一,为处理复杂系统的多重变化维度提供了一种优雅的解决方案,它将抽象部分与实现部分分离,使它们可以独立变化;

它是一种结构型设计模式,它的核心思想是**将抽象部分与实现部分分离,使它们可以独立变化**

这里我总结出的核心思想其实就是**拆解和组合**,拆解即`分离关注点`(当前模块中的关注点有两个:支付方式和验证方式),组合即`运行时绑定`

通过组合关系代替继承关系,降低了抽象和实现这两个可变维度的耦合度

### 结构

桥接模式包含以下主要角色:

1. **Abstraction(抽象类)**:定义抽象部分的接口,通常包含一个指向实现部分的引用,在模块中对应的是([PaymentMethod](src/main/java/com/niudada/pay/PaymentMethod.java))
2. **RefinedAbstraction(扩充抽象类)**:扩展抽象类,实现具体业务逻辑,在模块中对应的是([WeChatPay](src/main/java/com/niudada/pay/WeChatPay.java)...)
3. **Implementor(实现类接口)**:定义实现部分的接口,在模块中对应的是([PaymentVerification](src/main/java/com/niudada/pay/PaymentVerification.java))
4. **ConcreteImplementor(具体实现类)**:实现实现部分接口的具体实现,在模块中对应的是([FaceRecognition](src/main/java/com/niudada/pay/FaceRecognition.java)...)

### 传统模式 ~VS~ 桥接模式

在现代支付系统中,用户可以选择不同的支付平台(如微信、支付宝),同时可以使用不同的验证方式(如人脸识别、指纹、密码)

#### 传统模式

如果不使用桥接模式,我们可能会采用继承的方式来实现:

```java
class WeChatPayWithFace extends PaymentBase { /* ... */ }
class WeChatPayWithFingerprint extends PaymentBase { /* ... */ }
class AlipayWithFace extends PaymentBase { /* ... */ }
class AlipayWithFingerprint extends PaymentBase { /* ... */ }
// ... 更多组合类
```

这种方式会导致严重的**类爆炸问题**:如果有M种支付方式和N种验证方式，就需要创建M×N个类

#### 桥接模式

通过桥接模式,我们将支付方式和验证方式进行分离:

```java
// 实现部分:支付验证方式接口
public interface PaymentVerification {
    void verify();
    String getVerificationType();
}

// 具体实现类
public class FaceRecognition implements PaymentVerification {
    @Override
    public void verify() {
        System.out.println("正在进行人脸识别验证...");
        System.out.println("人脸识别验证通过");
    }
    
    @Override
    public String getVerificationType() {
        return "人脸识别";
    }
}

// 抽象部分:支付方式基类
public abstract class PaymentMethod {
    protected PaymentVerification verification;

    public PaymentMethod(PaymentVerification verification) {
        this.verification = verification;
    }

    public abstract void pay(double amount);
}

// 扩充抽象类
public class WeChatPay extends PaymentMethod {
    public WeChatPay(PaymentVerification verification) {
        super(verification);
    }

    @Override
    public void pay(double amount) {
        System.out.println("使用微信支付:");
        verification.verify();
        System.out.println("微信支付成功,金额: " + amount + "元");
    }
}
```

通过这种设计,我们只需要M+N个类就能实现M×N种组合,大大降低了代码复杂度

### 优势

#### 1. 避免类爆炸

刚才通过举例也证明了:桥接模式可以将多维度变化分离,能够避免类数量的指数级增长

#### 2. 提高扩展性

新增支付方式或验证方式都非常简单,只需添加相应的类,无需修改现有代码:

```java
// 新增Apple Pay支付方式
public class ApplePay extends PaymentMethod {
    // 实现细节...
}

// 新增声纹验证方式
public class VoiceprintVerification implements PaymentVerification {
    // 实现细节...
}
```

#### 3. 增强灵活性

可以在运行时动态组合不同的抽象和实现:

```java
PaymentVerification faceRecognition = new FaceRecognition();
PaymentMethod weChatPay = new WeChatPay(faceRecognition);
weChatPay.pay(100.0);
// 更多组合...
```


#### 4. 符合开闭原则

一句话:对扩展开放,对修改封闭,新增功能不需要修改现有代码,只需添加新的类

### 适用场景

桥接模式适用于以下场景:

1. **不希望在抽象和实现部分之间有固定的绑定关系(前提是有固定的绑定关系)**
2. **抽象和实现部分都需要通过子类进行扩展**
3. **对抽象的实现部分的改动不能对客户产生影响**
4. **想在多个对象间共享实现,但同时要求客户不知道这种共享**
5. **类的抽象只有一个实现,但程序需要在运行时动态选择实现**

### 与其他模式的区别

#### 桥接模式 vs 适配器模式

- **桥接模式**:在设计一开始就考虑将抽象和实现分离
- **适配器模式**:主要用于解决现有接口不兼容的问题

#### 桥接模式 vs 策略模式

- **桥接模式**:关注抽象和实现的分离,两者是"拥有"关系
- **策略模式**:关注算法族的封装,是"使用"关系

### 总结

在实际开发中,有效识别出系统中可以独立变化的部分,并通过桥接模式进行合理的设计,能够大大提升代码的可维护性和可扩展性

不过要注意,**桥接模式并不适合所有场景**,如果抽象和实现之间没有固定的绑定关系,那么就不适合使用桥接模式

还是那句话:
> 具体情况具体分析 / `Analyze each case on its own merits.`
> 
> 过度优化是万恶之源 / `Over-optimization is the root of all evil.`