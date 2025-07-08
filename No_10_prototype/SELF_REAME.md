# 原型(克隆)模式学习文档

---
## 一、原型模式简介

### 1.1 定义
原型模式(Prototype Pattern)是一种**创建型设计模式**，它通过**复制一个现有对象来创建新对象**，而不是通过`new`关键字直接构造

### 1.2 目的
- 避免复杂的构造逻辑；
- 提高性能：当对象创建成本较高时，使用克隆可以节省资源；
- 动态指定实例类型；

### 1.3 核心思想
> **“用已有对象作为模板，去克隆生成新的对象”**

---

## 二、三种实现方式对比

| 实现方式 | 技术手段 | 是否深拷贝 | 优点 | 缺点 |
|----------|-----------|-------------|------|------|
| 基础版 | 构造器手动赋值 | 否（浅拷贝） | 简单易懂，适合小项目 | 手动维护字段，易出错 |
| 浅拷贝 | `Object.clone()` | 否（仅复制引用） | Java原生支持，速度快 | 无法处理嵌套对象 |
| 深拷贝 | 序列化/反序列化 | 是（递归复制整个对象图） | 安全、完整、适用于复杂结构 | 性能略低，需实现 `Serializable` |

---

## 三、基础版实现(构造器赋值)

### 3.1 实现方式
- 每个类提供一个带参构造器，用于从已有对象复制数据

```java
public class ClothingSku extends ProductSku {
    public ClothingSku(ClothingSku source) {
        super(source);
        this.size = source.size;
        this.color = source.color;
    }

    @Override
    public ProductSku copy() {
        return new ClothingSku(this);
    }
}
```


### 3.2 特点
- 手动复制字段，可控性强；
- 对象之间独立，但嵌套对象仍共享引用；
- 不依赖 Java 原生机制，易于理解；

---

## 四、浅拷贝实现(clone)

### 4.1 实现方式
- 使用 Java 的 `Object.clone()` 方法进行克隆
- 类需要实现 `Cloneable` 接口，并重写 `clone()` 方法

```java
public abstract class Prototype<T> implements Cloneable {
    public T copy() {
        try {
            return (T) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("克隆失败", e);
        }
    }
}
```


### 4.2 特点
- 利用 Java 原生机制，效率高；
- 只复制对象本身，不复制其引用的对象；
- 修改克隆对象的嵌套对象会影响原始对象；

---

## 五、深拷贝实现(序列化)

### 5.1 实现方式
- 利用对象序列化与反序列化完成深拷贝
- 要求所有相关类都实现 `Serializable` 接口

```java
public abstract class Prototype<T> implements Serializable {
    public T deepCopy() {
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
             ObjectOutputStream oos = new ObjectOutputStream(bos)) {
            oos.writeObject(this);
            try (ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(bos.toByteArray()))) {
                return (T) ois.readObject();
            }
        } catch (Exception e) {
            throw new RuntimeException("深拷贝失败", e);
        }
    }
}
```


### 5.2 特点
- 复制整个对象图，包括嵌套对象；
- 安全性更高，适合复杂对象结构；
- 性能略低，且要求对象可序列化；

---

## 六、浅拷贝 vs 深拷贝(实践对比)

### 6.1 加入 Address 字段测试

我们在 [ClothingSku](src/main/java/com/niudada/sku/shallow_copy/ClothingSku.java) 中新增了一个 `Address address` 字段，分别在两种方式中进行测试：

#### 6.1.1 浅拷贝测试结果([ShallowCopyTest](src/test/java/com/niudada/sku/shallow_copy/ShallowCopyTest.java))

```java
ClothingSku tShirt1 = clothFactory.createProductSku();
ClothingSku tShirt2 = clothFactory.createProductSku();

tShirt2.getAddress().setCity("上海");

// 输出：
// tShirt1.address.city == "上海"
// tShirt2.address.city == "上海"
```


✅ **结论**：浅拷贝下，两个对象的 [address](src/main/java/com/niudada/sku/deep_copy/ClothingSku.java) 引用同一个地址对象，修改一方会影响另一方

#### 6.1.2 深拷贝测试结果([DeepCopyTest](src/test/java/com/niudada/sku/deep_copy/DeepCopyTest.java))

```java
ClothingSku tShirt3 = clothFactory.createProductSku();
ClothingSku tShirt4 = clothFactory.createProductSku();

tShirt4.getAddress().setCity("北京");

// 输出：
// tShirt3.address.city == "上海"
// tShirt4.address.city == "北京"
```


✅ **结论**：深拷贝下，两个对象完全独立，互不影响

---

## 七、应用场景建议

| 场景 | 推荐方式       |
|------|------------|
| 简单对象、无需嵌套 | 基础版        |
| 中小型对象、对性能敏感 | 浅拷贝        |
| 复杂对象、嵌套结构多 | 深拷贝        |
| 对象图中存在循环引用 | ❗ 深拷贝需谨慎处理 |
| 对象需持久化或传输 | 深拷贝(便于序列化) |

---

## 八、实践建议(具体情况具体分析)

1. **优先考虑深拷贝**，避免对象间意外共享状态
2. **合理选择拷贝方式**：
    - 简单对象 → 构造器赋值
    - 快速克隆 → `clone()`
    - 安全克隆 → 序列化
3. **注意嵌套结构**：
    - 若有嵌套对象，浅拷贝可能引发副作用
    - 深拷贝应确保所有嵌套类也实现 `Serializable`
4. **异常处理**：
    - 使用 try-catch 包裹 clone 或序列化过程

---


## 九、常见问题

### Q1: 如何判断是浅拷贝还是深拷贝？

A：修改克隆对象的嵌套对象属性，看是否影响原始对象：若影响，则为浅拷贝；若不影响，则为深拷贝

---

### Q2: clone() 和 序列化哪个更推荐？

A：根据需求而定：
- 如果只复制基本类型字段，`clone()` 更快
- 如果涉及嵌套对象或集合，建议使用深拷贝(序列化)

---

### Q3: 如何优化深拷贝性能？

A：可使用以下方式替代默认序列化：
- Apache Commons Lang 的 `SerializationUtils.clone()`
- 第三方库如 Dozer、ModelMapper、MapStruct
- 自定义深拷贝构造器(适用于已知结构)

---