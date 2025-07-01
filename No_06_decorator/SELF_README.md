# 装饰器模式学习文档

## 一、项目背景

在面向对象的设计中，装饰器模式是一种用于动态地给对象添加职责的设计模式。它提供了一种灵活的替代方案来扩展功能，而不需要通过子类化来实现。

在这个项目中，我们使用了一个东方神话故事的背景，描述一个修仙者如何通过获得不同的法宝（如宝剑和盾牌）来增强自己的战斗能力。这个例子很好地诠释了装饰器模式的应用。

---

## 二、装饰器模式结构

[结构图](demo.puml)

---

## 三、核心代码解析

### 1. 接口定义：[Immortal.java](src/main/java/com/niudada/Immortal.java)

```java
public interface Immortal {
    void attack();
    int getPower();
    void retreat();
}
```


该接口定义了所有修仙者必须具备的基本能力。

---

### 2. 基础类：[BasicImmortal.java](src/main/java/com/niudada/BasicImmortal.java)

```java
@Slf4j
public class BasicImmortal implements Immortal {
    @Override
    public void attack() {
        log.info("修仙者施展[基础功法]攻击!");
    }

    @Override
    public int getPower() {
        return 50;
    }

    @Override
    public void retreat() {
        log.info("说时迟那时快! 修仙者施展[遁术]转眼便没了踪影...");
    }
}
```


这是最基础的修仙者实现，实现了基本的攻击、灵力修为和撤退方法。

---

### 3. 装饰器抽象类：[SwordDecorator.java](src/main/java/com/niudada/SwordDecorator.java)

```java
@Slf4j
public class SwordDecorator implements Immortal {
    private final Immortal decoratedImmortal;

    public SwordDecorator(Immortal immortal) {
        this.decoratedImmortal = immortal;
    }

    @Override
    public void attack() {
        decoratedImmortal.attack();
        log.info("青龙宝剑出鞘，发出龙吟般的剑鸣！");
    }

    @Override
    public int getPower() {
        return decoratedImmortal.getPower() + 30;
    }

    @Override
    public void retreat() {
        decoratedImmortal.retreat();
    }
}
```


为修仙者增加一把“青龙宝剑”，增强了攻击力。

---

### 4. 装饰器抽象类：[ShieldDecorator.java](src/main/java/com/niudada/ShieldDecorator.java)

```java
@Slf4j
public class ShieldDecorator implements Immortal {
    private final Immortal decoratedImmortal;

    public ShieldDecorator(Immortal immortal){
        this.decoratedImmortal = immortal;
    }

    @Override
    public void attack() {
        decoratedImmortal.attack();
    }

    @Override
    public int getPower() {
        return decoratedImmortal.getPower() + 20;
    }

    @Override
    public void retreat() {
        log.info("玄武盾牌展开，形成一道防御屏障！");
        decoratedImmortal.retreat();
    }
}
```


为修仙者增加一个“玄武盾牌”，提高了防御能力，并在撤退时展示特殊效果。

---

## 四、Main 主函数演示

### [Main.java](src/main/java/com/niudada/Main.java)

```java
public class Main {
    public static void main(String[] args) {
        // 基础修仙者
        Immortal immortal = new BasicImmortal();
        System.out.println("普通修仙者能力展示：");
        System.out.println("灵力修为：" + immortal.getPower());
        immortal.attack();

        // 装备青龙宝剑
        Immortal swordImmortal = new SwordDecorator(immortal);
        System.out.println("\n装备青龙宝剑之后的修仙者：");
        System.out.println("灵力修为：" + swordImmortal.getPower());
        swordImmortal.attack();

        // 同时装备青龙宝剑和玄武盾牌
        Immortal shieldImmortal = new ShieldDecorator(new SwordDecorator(immortal));
        System.out.println("\n同时装备青龙宝剑和玄武盾牌之后的修仙者：");
        System.out.println("灵力修为：" + shieldImmortal.getPower());
        shieldImmortal.attack();
        shieldImmortal.retreat();
    }
}
```


### 输出结果示例：

```
普通修仙者能力展示：
灵力修为：50
INFO: 修仙者施展[基础功法]攻击!

装备青龙宝剑之后的修仙者：
灵力修为：80
INFO: 修仙者施展[基础功法]攻击!
INFO: 青龙宝剑出鞘，发出龙吟般的剑鸣！

同时装备青龙宝剑和玄武盾牌之后的修仙者：
灵力修为：100
INFO: 修仙者施展[基础功法]攻击!
INFO: 青龙宝剑出鞘，发出龙吟般的剑鸣！
INFO: 玄武盾牌展开，形成一道防御屏障！
INFO: 说时迟那时快! 修仙者施展[遁术]转眼便没了踪影...
```


---

## 五、装饰器模式特点分析

### ✅ 动态添加功能
- 可以在运行时决定是否为对象添加新功能
- 不需要提前在类中定义所有可能的功能
- 示例：`SwordDecorator` 和 `ShieldDecorator` 动态增强了修仙者的能力

### ✅ 组合优于继承
- 没有为每种组合创建子类
- 使用组合方式灵活构建所需功能
- 示例：`new ShieldDecorator(new SwordDecorator(immortal))` 实现多重装饰

### ✅ 保持对象核心职责清晰
- `BasicImmortal` 只关注基础功能
- 每个装饰器只关注自己的职责
- 符合单一职责原则

---

## 六、常见问题与解答

### Q1: 为什么不能直接修改基础类？
**A:** 修改基础类会导致原有逻辑混乱，违背开闭原则. 装饰器模式允许我们在不修改已有代码的情况下扩展功能

### Q2: 能否替换装饰顺序？
**A:** 可以,例如：
```java
new SwordDecorator(new ShieldDecorator(immortal))
```

这会影响执行顺序，但不会影响最终功能

### Q3: 如果装饰器很多，会不会很麻烦？
**A:** 是的，如果装饰器很多，可以考虑以下优化方式：

#### 方案1：分步创建
```java
Immortal swordImmortal = new SwordDecorator(immortal);
Immortal fullImmortal = new ShieldDecorator(swordImmortal);
```


#### 方案2：使用工厂方法
```java
public class ImmortalFactory {
    public static Immortal createFullImmortal(Immortal base) {
        return new ShieldDecorator(new SwordDecorator(base));
    }
}
```


#### 方案3：使用建造者模式
```java
public class ImmortalBuilder {
    private Immortal baseImmortal;

    public ImmortalBuilder() {
        this.baseImmortal = new BasicImmortal();
    }

    public ImmortalBuilder withSword() {
        this.baseImmortal = new SwordDecorator(this.baseImmortal);
        return this;
    }

    public ImmortalBuilder withShield() {
        this.baseImmortal = new ShieldDecorator(this.baseImmortal);
        return this;
    }

    public Immortal build() {
        return this.baseImmortal;
    }
}

// 使用方式
Immortal immortal = new ImmortalBuilder()
                        .withSword()
                        .withShield()
                        .build();
```


---

## 七、总结

### 📌 装饰器模式的优点
- **灵活性强**：可以在运行时动态添加功能
- **可维护性好**：每个类职责单一，易于维护
- **符合设计原则**：开闭原则、单一职责原则

### ⚠️ 注意事项
- 装饰器链过长可能导致调试困难
- 调用顺序会影响行为表现
- 需要合理组织装饰器层级关系

### ✅ 适用场景
- 需要动态、透明地给对象添加职责
- 不希望通过大量子类来扩展功能
- 遵循开闭原则和单一职责原则的场景

---

## 八、参考资料

- [Java IO 流中的装饰器模式](https://www.journaldev.com/1540/decorator-design-pattern-in-java-example)
- [GOF 设计模式书籍](https://www.amazon.com/gp/product/0201633612)
- [Head First 设计模式](https://www.amazon.com/gp/product/0596007124)

---