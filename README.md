# Java设计模式（iluwatar/java-design-patterns）全量学习路径与建议

###### 免责条款：当前项目仅做个人记录，当前文档及本项目其他相关文档皆是建议，不是must-do

java-design-patterns项目每个英文目录即为一种设计模式或架构模式实现，极少数为项目文档或脚本。为帮助系统性学习，现将所有目录分为：

- 设计模式（细分为GoF 23种、架构型/企业级/并发/其他现代模式）
- 非设计模式（简要说明其用途）

## 本项目介绍
通过对java-design-patterns的整理，尝试将所有目录进行重建分类，使项目更加清楚，学习更加得心应手，最后给出学习建议。

如果你要学习某个设计模式：`mode-demo`,下面是建议的步骤(建议而已.这只是比较适合我的学习方法)：
1. 在当前项目中添加对应的模块`mode-demo`，使用 <span style="color:skyblue;cursor:pointer;">No_xxx_模式名</span>`No_xxx_mode-demo`命名，利于排序
2. 将java-design-patterns中对应模式的中文文档和UML图添加到对应目录，为帮助理解和后面的学习增加效率

之后就可以根据中文文档和UML图模式进行学习了，可以选择自己实现一遍，也可以输出到自己的知识md文档，方便自己查阅。

## 学习前建议

- **先掌握基础设计原则**：如 KISS、YAGNI、单一职责、开闭原则等。
- **学习顺序建议**：先易后难，优先掌握经典（GoF）模式，从创建型到结构型，再到行为型，最后扩展到架构型和其他现代模式。


## 一、项目中典型非设计模式目录说明

| 目录名         | 类型     | 说明 |
|----------------|----------|------|
| .github        | 辅助     | GitHub配置、CI/CD、issue模板等 |
| docs           | 辅助     | 项目文档/开发说明            |
| localization   | 辅助     | 多语言文档                   |
| scripts        | 辅助     | 自动化脚本                   |
| bin            | 辅助     | 可执行脚本/工具              |
| etc            | 辅助     | 静态资源、脚本、UML图等      |
| images         | 辅助     | 图示资源                     |

这些目录**不是设计模式实现**，可直接略过。其余英文目录（如`singleton`、`strategy`、`event-sourcing`等）均为具体设计模式实现。

---

## 二、GoF 23种设计模式（项目均有实现）

| 英文名                | 中文名         | 类型     | 目录名           | 难度建议  |
|-----------------------|----------------|----------|------------------|-----------|
| Abstract Factory      | 抽象工厂       | 创建型   | abstract-factory | 入门-进阶 |
| Builder               | 建造者         | 创建型   | builder          | 入门      |
| Factory Method        | 工厂方法       | 创建型   | factory-method   | 入门      |
| Prototype             | 原型           | 创建型   | prototype        | 入门      |
| Singleton             | 单例           | 创建型   | singleton        | 入门      |
| Adapter               | 适配器         | 结构型   | adapter          | 入门      |
| Bridge                | 桥接           | 结构型   | bridge           | 进阶      |
| Composite             | 组合           | 结构型   | composite        | 进阶      |
| Decorator             | 装饰器         | 结构型   | decorator        | 入门      |
| Facade                | 外观           | 结构型   | facade           | 入门      |
| Flyweight             | 享元           | 结构型   | flyweight        | 进阶      |
| Proxy                 | 代理           | 结构型   | proxy            | 入门      |
| Chain of Responsibility | 责任链        | 行为型   | chain            | 进阶      |
| Command               | 命令           | 行为型   | command          | 进阶      |
| Interpreter           | 解释器         | 行为型   | interpreter      | 高阶      |
| Iterator              | 迭代器         | 行为型   | iterator         | 入门      |
| Mediator              | 中介者         | 行为型   | mediator         | 进阶      |
| Memento               | 备忘录         | 行为型   | memento          | 进阶      |
| Observer              | 观察者         | 行为型   | observer         | 入门      |
| State                 | 状态           | 行为型   | state            | 入门      |
| Strategy              | 策略           | 行为型   | strategy         | 入门      |
| Template Method       | 模板方法       | 行为型   | template-method  | 入门      |
| Visitor               | 访问者         | 行为型   | visitor          | 高阶      |

---

## 三、架构型/企业级/并发/现代模式（项目均有实现）

| 英文名                  | 中文名           | 目录名                 | 类型/场景    |
|-------------------------|------------------|------------------------|--------------|
| Event Sourcing          | 事件溯源         | event-sourcing         | 架构         |
| CQRS                    | 命令查询分离     | cqrs                   | 架构         |
| Repository              | 仓储模式         | repository             | 数据访问     |
| Data Mapper             | 数据映射器       | data-mapper            | 数据访问     |
| Table Module            | 表模块           | table-module           | 数据访问     |
| Data Transfer Object    | 数据传输对象     | dto                    | 架构         |
| Service Locator         | 服务定位器       | service-locator        | 架构         |
| Dependency Injection    | 依赖注入         | dependency-injection    | 架构         |
| API Gateway             | API网关          | api-gateway            | 架构         |
| Aggregator              | 聚合器           | aggregator              | 架构         |
| Circuit Breaker         | 断路器           | circuit-breaker         | 并发/微服务  |
| Rate Limiter            | 限流器           | rate-limiter            | 并发/微服务  |
| Reactor                 | Reactor模式      | reactor                 | 并发         |
| Thread Pool             | 线程池           | thread-pool             | 并发         |
| Promise, Future, Guarded Suspension 等 | 并发相关 | promise, future, guarded-suspension | 并发         |

**还有大量模式**如：Specification、Object Pool、Double Checked Locking、Null Object、Marker、Dirty Flag、Monostate、Property、Publish-Subscribe、Business Delegate、Service Layer、Converter、Fluent Interface、Extension Object、Composite Entity、Intercepting Filter、Model View Controller、Module、Page Object、Registry、Saga、Value Object 等，均有相应目录实现。

---

## 四、完整学习路径推荐（分阶段）

### 第一阶段：基础设计模式（GoF+常用架构型）

- 推荐顺序：**创建型 → 结构型 → 行为型 → 架构型/企业级基础**
    - 适合初学者，打好基础，理解常见编程场景。

### 第二阶段：进阶模式（并发、分布式、企业级）

- 深入并发、分布式、数据访问、企业级架构相关模式
    - 适合有项目开发经验者，提升系统设计能力。

### 第三阶段：现代架构与微服务相关模式

- 关注 CQRS、Event Sourcing、API Gateway、Rate Limiter、Saga、Reactor 等
    - 适合架构师与有大规模系统开发需求者。

---

## 学习建议

1. **优先掌握GoF 23种经典模式**（参考“是否GoF”列），这些是面试与实际开发中最常用的基础。
2. **结合实际项目需要选择性深入**现代/架构型模式，如依赖注入、CQRS、事件溯源等，这些对复杂系统设计和微服务架构尤为重要。
3. **多对比不同模式间的适用场景和优缺点**，理解模式背后的设计思想而不仅仅是实现代码。
4. **善用项目代码中的注释和示例**，可将其当作编程教程逐步实践。
5. **建议学习顺序**：先“创建型”→再“结构型”→后“行为型”→最后“架构型/现代模式”。
6. **不要为用模式而用模式**，优先考虑简单方案，只有在复杂度和扩展性确实需要时再引入设计模式。

---

> **温馨提示**：  
> 项目内容庞杂，建议结合[官方 README 中文版](https://github.com/iluwatar/java-design-patterns/blob/master/localization/zh/README.md)和每个子目录下的 README 学习，可更快定位到模式原理与代码实现。
> 每个模式目录下通常有详细注释源码、测试用例和UML图，强烈建议结合阅读。