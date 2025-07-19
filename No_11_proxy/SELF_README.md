# 📚 代理模式(Proxy Pattern)学习文档

## 🧠 一、什么是代理模式?

> **代理模式(Proxy Pattern)** 是一种结构型设计模式，用于为对象提供一个代理或占位符以控制其访问

### ✅ 核心思想：
- 客户端不直接访问真实对象，而是通过代理对象间接访问
- 代理可以在调用前后添加额外逻辑(如权限检查、日志记录、缓存等),而无需修改真实对象

---

## 📦 二、核心角色说明

| 角色 | 职责 |
|------|------|
| `Subject` | 公共接口，定义代理和真实对象共同实现的方法 |
| `RealSubject` | 实现接口的具体类，完成实际业务功能 |
| `Proxy` | 包含对 `RealSubject` 的引用，在调用前后添加附加逻辑 |

---

## 🧪 三、Java 中的代理实现方式

### ✅ 1. 手动编码实现(静态代理)
- 手动编写接口、真实类、代理类
- 适用于逻辑简单、代理数量少的情况

```java
interface Service {
    void doSomething();
}

class RealService implements Service {
    public void doSomething() { System.out.println("执行真实任务"); }
}

class LoggingProxy implements Service {
    private final Service real;

    public LoggingProxy(Service real) { this.real = real; }

    public void doSomething() {
        System.out.println("开始调用");
        real.doSomething();
        System.out.println("结束调用");
    }
}
```


---

### ✅ 2. 使用JDK动态代理(Dynamic Proxy)

适用于基于接口的代理

```java
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class DynamicProxy implements InvocationHandler {
    private final Object target;

    public DynamicProxy(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("前置处理");
        Object result = method.invoke(target, args);
        System.out.println("后置处理");
        return result;
    }
}
```


使用示例：

```java
Service service = (Service) Proxy.newProxyInstance(
    target.getClass().getClassLoader(),
    new Class[]{Service.class},
    new DynamicProxy(realService)
);
```


---

### ✅ 3. 使用CGLIB动态代理(子类代理)

适用于没有接口的类代理

```java
Enhancer enhancer = new Enhancer();
enhancer.setSuperclass(RealService.class);
enhancer.setCallback((MethodInterceptor) (obj, method, args, proxy) -> {
    System.out.println("前置处理");
    Object result = proxy.invokeSuper(obj, args);
    System.out.println("后置处理");
    return result;
});
RealService proxy = (RealService) enhancer.create();
```


---

## 📚 四、场景Demo总览

> 选择性学习即可，目的是理解，不用`necessarily`全部学习
> 
> ⚠️另外这些demo也不一定准确，如果存在错误，请多包涵

| 编号 | 场景 | 目标 |
|----|------|------|
| 1  | 权限控制代理 | 控制用户访问敏感资源 |
| 2  | 缓存代理 | 避免重复查询数据库/网络 |
| 3  | 懒加载代理 | 延迟初始化昂贵对象 |
| 4  | 限流代理 | 控制请求频率，防止系统过载 |
| 5  | 日志记录代理 | 记录方法调用日志 |
| 6  | 远程调用代理 | 封装远程服务调用细节 |

---

## 📁 五、项目结构建议

```
com.niudada.proxydemo
├── auth        // 权限控制
├── cache       // 缓存代理
├── lazy        // 懒加载代理
├── limit       // 限流代理
├── log         // 日志记录代理
├── remote      // 远程调用代理
```

---

## 📝 六、优缺点分析

### ✅ 优点
- 解耦客户端与真实对象
- 可扩展性强，可在不修改真实对象的前提下添加新功能
- 支持多种代理类型，适应不同需求

### ❌ 缺点
- 增加了系统的复杂度
- 若代理过多，可能影响性能
- 对新手来说理解成本较高

---

## 🚀 其他
这些demo太简单了，如果你学的还不过瘾，你可以继续深入

| 方向           | 描述 |
|-----|------|
| `Maven`多模块   |将`remote`拆分为独立`Maven`模块，实现真正的远程代理|
| `RPC`框架      |结合`Dubbo`、`gRPC`等框架实践真正的远程代理|
| `Spring AOP` |使用`Spring AOP`替代手动代理，统一处理日志、权限等逻辑|
