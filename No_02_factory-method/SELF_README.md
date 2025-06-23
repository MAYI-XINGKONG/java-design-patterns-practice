# 工厂方法模式学习文档

## 一、什么是工厂方法模式？

**工厂方法模式（Factory Method Pattern）** 是一种常用的对象创建型设计模式，属于创建型模式范畴；它的主要目的是将对象的创建过程封装起来，使得客户端代码不需要关心具体的对象创建细节

> **定义**：为创建一个对象定义一个接口，但是让子类决定实例化哪个类,工厂方法允许类将实例化延迟到子类

简单来说，就是把“new 对象”的操作交给子类去完成，父类只负责提供接口

---

## 二、核心结构与角色

工厂方法模式有以下核心角色：


| 角色                          | 说明                                                                                                                                                |
| ----------------------------- |---------------------------------------------------------------------------------------------------------------------------------------------------|
| `Product`（产品接口）         | 定义要创建的对象的公共接口，如[Car](src/main/java/com/niudada/product/Car.java) 接口                                                                               |
| `ConcreteProduct`（具体产品） | 实现了`Product` 接口的具体类，如 [BmwCar](src/main/java/com/niudada/concrete/bmw/BmwCar.java)、[BydCar](src/main/java/com/niudada/concrete/byd/BydCar.java) 等 |
| `Factory`（工厂接口）         | 定义创建产品的抽象方法，如[CarFactory](src/main/java/com/niudada/factory/CarFactory.java) 接口                                                                   |
| `ConcreteFactory`（具体工厂） | 实现工厂接口的方法来返回一个具体的产品实例，如[BmwCarFactory](src/main/java/com/niudada/carfactory/BmwCarFactory.java)                                                   |

---

## 三、示例解析

### 1. 产品接口 [Car](src/main/java/com/niudada/product/Car.java)

```java
public interface Car {
    void assemble();
}
```

### 2. 具体产品 [BmwCar](src/main/java/com/niudada/concrete/bmw/BmwCar.java)

```java
public class BmwCar implements Car {
    private final Engine engine;
    private final Tire tire;

    public BmwCar(EngineFactory engineFactory, TireFactory tireFactory){
        this.engine = engineFactory.createEngine();
        this.tire = tireFactory.createTire();
    }

    @Override
    public void assemble() {
        engine.build();
        tire.build();
        System.out.println("Assembling BMW Car");
    }
}
```

### 3. 工厂接口 `CarFactory`

```java
public interface CarFactory {
    Car createCar(EngineFactory engineFactory, TireFactory tireFactory);
}
```

### 4. 具体工厂 `BmwCarFactory`

```java
public class BmwCarFactory implements CarFactory {
    @Override
    public Car createCar(EngineFactory engineFactory, TireFactory tireFactory) {
        return new BmwCar(engineFactory, tireFactory);
    }
}
```

### 5. 客户端使用方式

```java
public class Main {
    public static void main(String[] args) {
        BmwCarFactory bmwCarFactory = new BmwCarFactory();
        BmwEngineFactory bmwEngineFactory = new BmwEngineFactory();
        BmwTireFactory bmwTireFactory = new BmwTireFactory();

        Car bmwCar = bmwCarFactory.createCar(bmwEngineFactory, bmwTireFactory);
        bmwCar.assemble();
    }
}
```

---

## 四、优点


| 优点       | 描述                                                       |
| ---------- | ---------------------------------------------------------- |
| 解耦       | 客户端不直接依赖具体类，而是通过接口调用，便于扩展和替换 |
| 可扩展性强 | 增加新产品时只需新增对应的工厂和产品类，符合开闭原则     |
| 封装性好   | 创建对象的过程集中管理，隐藏实现细节                     |

---

## 五、适用场景

- 当一个类不知道它所必须创建的对象的类时
- 当一个类希望由子类来指定它所创建的对象
- 当你希望将对象的创建逻辑集中并统一管理时

---

## 六、实际应用举例（Java标准库）

- `Calendar.getInstance()`：根据系统环境返回不同的 `Calendar` 实现
- `NumberFormat.getInstance()`：返回数字格式化的具体实现
- `URL.openConnection()`：根据协议返回不同的连接实现

---

## 七、总结

工厂方法模式的核心思想是 **“延迟到子类”**，即父类定义创建对象的接口，但具体由哪个类被创建，由子类决定

在当前项目中，[CarFactory](src/main/java/com/niudada/factory/CarFactory.java) 接口定义了创建汽车的方式，而每个品牌（如宝马、比亚迪、特斯拉）都实现了自己的工厂类来创建对应品牌的汽车。这样做的好处是：

- 扩展方便，增加新品牌只需新增一套工厂+产品类；
- 避免了复杂的 `if-else` 或 `switch-case` 判断；
- 提高了代码的可维护性和可测试性；
