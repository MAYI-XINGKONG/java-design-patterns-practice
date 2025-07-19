# 组合模式(Composite Pattern)实践总结与学习指南
**作者：@niudada**  
**时间：2025年7月**

---

## 一、组合模式的核心：统一性

组合模式的核心在于：
- 抽象出一个统一的接口或抽象类
- 叶子节点和组合节点都实现该接口
- 组合节点维护子节点集合,并递归调用
- 客户端无需关心具体类型,统一处理

> **也就是客户端无需区分叶子节点和组合节点,统一调用接口即可**

这种统一性在很多系统中都存在：

- 文件系统：目录包含目录和文件
- 菜单系统：菜单组包含菜单组和菜单项
- 审批流程：审批组包含审批组和审批节点
- API 路由：服务包含服务、组、接口
- 行为树：行为节点包含序列、选择、动作

这些结构都具有**部分-整体**的特性,而组合模式正适合这种结构

---

## 二、Demo总结

### 1. **抽象类是组合模式的骨架**

在这些 Demo中,我都使用了抽象类作为统一接口,比如：

| 抽象类 | 作用 |
|--------|------|
| [FileSystemNode](src/main/java/com/niudada/level1/filesystem/FileSystemNode.java) | 文件系统统一接口 |
| [MenuComponent](src/main/java/com/niudada/level2/multilevelmenu/MenuComponent.java) | 菜单系统统一接口 |
| [BehaviorNode](src/main/java/com/niudada/level2/aibehaviortree/BehaviorNode.java) | 行为树统一接口 |
| [ApprovalComponent](src/main/java/com/niudada/level3/approvalflow/ApprovalComponent.java) | 审批流程统一接口 |
| [ApiComponent](src/main/java/com/niudada/level3/apirouter/ApiComponent.java) | API 路由统一接口 |

因为抽象类可以定义统一的行为,比如：

```java
// `indent`不用过分关注,只是为了展示出树形结构而已
public abstract void print(String indent);
public abstract void execute(String indent);
public abstract boolean approve(ApprovalContext context, String indent);
```

这些方法构成了组合模式的`"统一行为"`

---

### 2. **叶子节点和组合节点的职责**

组合模式的两个核心角色是：

- **叶子节点(Leaf)**：执行具体逻辑,不能添加子节点
- **组合节点(Composite)**：维护子节点集合,递归调用子节点

例如：

- [File](src/main/java/com/niudada/level1/filesystem/File.java) 是叶子节点,不能添加子节点
- [Directory](src/main/java/com/niudada/level1/filesystem/Directory.java) 是组合节点,可以添加子节点并递归打印

这种职责划分很清晰,是组合模式结构稳定的关键

---

### 3. **组合结构的递归调用**

> 提醒：递归调用不当容易导致内存溢出和泄露,所以使用需要谨慎

组合模式的关键在于递归调用,比如：

```java
public void print(String indent) {
    System.out.println(indent + "📁 目录：" + name);
    for (var child : children) {
        child.print(indent + "  ");
    }
}
```


只要调用顶层节点的 [print()](src/main/java/com/niudada/level1/filesystem/FileSystemNode.java) 方法,整个结构就会被递归遍历,客户端不用关心结构深度

---

### 4. **组合模式不是万能**

虽然组合模式很强大,但也不是所有结构都适合用它处理, 比如： 节点行为差异较大、或者结构简单层次少的

另外也有比较适合组合模式的场景：

- 需要处理树形结构、嵌套结构
- 需要统一处理叶子和组合
- 需要递归操作
- 需要动态构建结构

注意：组合模式不是万能的,它需要根据具体场景来选择使用,使用不当也可能会增加复杂度,让代码显得冗余

---

### 5. **组合模式的使用建议**

- 当对象之间存在`部分-整体`关系时,可以考虑组合模式
- 设计组件接口时,要统一叶子和组合的行为,避免出现大量 `instanceof`判断
- 避免滥用,如果结构简单,用组合模式反而会增加复杂度
- 注意空操作处理,如`add/remove`方法在叶子节点中应抛出异常或者不实现
- 结合其他模式使用,组合模式不是孤立的,它适合与其他模式结合使用,构建更复杂的系统

---

## 三、组合模式与其他模式
### 1.**对比**
| 模式 | 区别                                |
|----|-----------------------------------|
|装饰器模式| 组合是结构树,装饰器是行为链;组合用于组合结构,装饰器用于增强功能 |
|职责链模式| 组合是静态结构,职责链是动态处理流程                |
|迭代器模式| 组合中通常包含集合,迭代器用于遍历这些集合             |
|策略模式| 组合是结构,策略是行为选择                     |

### 2.**结合**
| 组合模式 | 其他模式 | 用途 |
|----|------|----|
|文件系统| 策略模式 |不同平台的渲染策略|
|菜单系统| 构建器模式 |更清晰的构建方式|
|审批流程| 状态模式 |审批中断、继续、失败|

---

## 四、我的一点感悟

> 组合模式不是最难的模式,但却是最实用的模式之一

> **它的价值在于它能帮你构建出结构清晰、逻辑统一、易于维护的系统**

> **它的难点不在于写,而在于"何时用"和"怎么用",这也是设计模式的一个难点**

---

## 五、组合模式设计模板(仅供参考)

```java
// 抽象组件
public abstract class Component {
    public abstract void operation();
    public void add(Component c) { throw new UnsupportedOperationException(); }
    public void remove(Component c) { throw new UnsupportedOperationException(); }
    public List<Component> getChildren() { throw new UnsupportedOperationException(); }
}

// 叶子节点
public class Leaf extends Component {
    @Override
    public void operation() {
        // 实现具体逻辑
    }
}

// 组合节点
public class Composite extends Component {
    private List<Component> children = new ArrayList<>();

    @Override
    public void operation() {
        for (Component child : children) {
            child.operation();
        }
    }

    @Override
    public void add(Component c) {
        children.add(c);
    }

    @Override
    public void remove(Component c) {
        children.remove(c);
    }

    @Override
    public List<Component> getChildren() {
        return children;
    }
}
```


---

## 六、感谢

感谢你花时间阅读这些内容,也感谢你愿意去尝试、去实践、去思考

> **我只是一个普通的开发者,写了一些普通的 Demo,希望它们能帮到你**

如果你有任何疑问、建议或改进,欢迎随时交流