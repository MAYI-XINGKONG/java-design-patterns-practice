# 观察者模式学习文档

---

## 🧠 一、观察者模式简介

> 看完代码记得再回来瞟一眼👀️

### 定义

观察者模式(Observer Pattern)是一种**行为型设计模式**，它定义了对象之间的一对多依赖关系；当一个对象的状态发生改变时，所有依赖它的对象都会自动收到通知并更新

### 别名

- 发布/订阅模式(Publish/Subscribe)
- 消息模型

### 核心思想

- **松耦合**：主题与观察者之间通过接口通信，无需了解具体实现
- **一对多通知机制**：一个主题可以有多个观察者，状态变化时统一通知
- **实时响应**：状态变化后，所有观察者都能立即做出反应

---

## 🌤️ 二、业务场景模拟：“天气预警系统”

### 场景描述

在气象局中，天气不断发生变化(如晴天☀️ → 雨天☔️ → 多云⛅️ → 雪天❄️)，多个终端设备(如手机App、广播电台、智能设备)需要及时接收最新天气信息，并作出相应的提示或动作

### 角色划分


| 角色                 | 类名 / 接口名                                                                                                                                                                                                         | 职责说明                         |
| -------------------- | --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | -------------------------------- |
| 主题(Subject)        | [Weather.java](src/main/java/com/niudada/subject/Weather.java)                                                                                                                                                        | 管理观察者列表，负责通知状态变化 |
| 观察者接口(Observer) | [WeatherObserver.java](src/main/java/com/niudada/observer/interfaces/WeatherObserver.java)                                                                                                                            | 定义更新方法，供具体观察者实现   |
| 具体观察者           | [PhoneApp.java](src/main/java/com/niudada/observer/PhoneApp.java), [RadioStation.java](src/main/java/com/niudada/observer/RadioStation.java), [SmartDevice.java](src/main/java/com/niudada/observer/SmartDevice.java) | 实现自己的响应逻辑               |

---

## 🧱 三、代码解析

### 1. 枚举类：[WeatherType.java](src/main/java/com/niudada/enums/WeatherType.java)

```java
public enum WeatherType {
    SUNNY(EmojiConstants.SUNNY),
    RAINY(EmojiConstants.RAINY),
    CLOUDY(EmojiConstants.CLOUDY),
    SNOWY(EmojiConstants.SNOWY);

    private final String description;

    WeatherType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
```

> ✅ 作用：表示天气类型及其对应的描述和表情符号

---

### 2. 观察者接口：[WeatherObserver.java](src/main/java/com/niudada/observer/interfaces/WeatherObserver.java)

```java
public interface WeatherObserver {
    void update(WeatherType currentWeather);
}
```

> ✅ 作用：定义所有观察者的通用行为(接收到天气变化时执行的更新操作)

---

### 3. 主题类：[Weather.java](src/main/java/com/niudada/subject/Weather.java)

```java
@Slf4j
public class Weather {
    private WeatherType currentWeather;
    private final List<WeatherObserver> observers;

    public Weather() {
        observers = new ArrayList<>();
        currentWeather = WeatherType.SUNNY;
    }

    public void addObserver(WeatherObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(WeatherObserver observer) {
        observers.remove(observer);
    }

    /**
     * 模拟天气变化
     * WeatherType.values(): 返回数组[SUNNY, RAINY, CLOUDY, SNOWY]，按顺序排列，索引分别是[0,1,2,3]
     * currentWeather.ordinal(): 获取当前天气在枚举中的索引值
     */
    public void timePasses() {
        WeatherType[] values = WeatherType.values();
        WeatherType tempCurrentWeather = currentWeather;
        // 获取下一个天气('% values.length': 取模操作，确保不会越界，实现循环效果)
        currentWeather = values[(currentWeather.ordinal() + 1) % values.length];
        log.info("广大市民请注意：近期天气将由*{}*转为*{}*",
                tempCurrentWeather.getDescription(),
                currentWeather.getDescription());
        notifyObservers();
    }

    /**
     * 观察者模式中“通知机制”的核心实现
     * 所有注册的观察者都能及时收到最新状态并作出反应
     */
    private void notifyObservers() {
        // 遍历所有实现了WeatherObserver接口的观察者类
        for (WeatherObserver observer : observers) {
            // 每个观察者根据最新的currentWeather做出自己的响应
            observer.update(currentWeather);
        }
    }
}
```

> ✅ 作用：
>
> - 维护观察者列表；
> - 在天气变化时调用 [notifyObservers()](src/main/java/com/niudada/subject/Weather.java) 方法，通知所有观察者。

---

### 4. 具体观察者类(以 [PhoneApp.java](src/main/java/com/niudada/observer/PhoneApp.java) 为例)

```java
@Slf4j
public class PhoneApp implements WeatherObserver {

    @Override
    public void update(WeatherType currentWeather) {
        log.info("手机收到天气更新，当前天气为：{}", currentWeather.getDescription());
    }
}
```

> 类似的[RadioStation](src/main/java/com/niudada/observer/RadioStation.java) 和 [SmartDevice](src/main/java/com/niudada/observer/SmartDevice.java) 也实现了各自的`update()` 方法。

---

### 5. 启动类：[Main.java](src/main/java/com/niudada/Main.java)

```java
@Slf4j
public class Main {
    public static void main(String[] args) {
        //注册观察者
        Weather weather = new Weather();
        weather.addObserver(new PhoneApp());
        weather.addObserver(new RadioStation());
        weather.addObserver(new SmartDevice());

        // 模拟天气变化
        for (int i = 0; i < 5; i++) {
            // 模拟日期变化
            log.info(DateUtil.getFutureDate(i) + "日" + EmojiConstants.DUSK + "通知：");
            weather.timePasses();
            System.out.println();
        }
    }
}
```

> 🚀 输出示例：

```
10:53:41.675 [main] INFO com.niudada.Main -- 2025-07-03日🌆通知：
10:53:41.677 [main] INFO com.niudada.subject.Weather -- 广大市民请注意：近期天气将由*☀️*转为*🌧️(☔️)*
10:53:41.678 [main] INFO com.niudada.observer.PhoneApp -- 手机收到天气更新，当前天气为：🌧️(☔️)
10:53:41.678 [main] INFO com.niudada.observer.RadioStation -- 广播电台收到天气更新，当前天气为：🌧️(☔️)
10:53:41.679 [main] INFO com.niudada.observer.SmartDevice -- 其他智能设备收到天气更新，当前天气为：🌧️(☔️)

10:53:41.679 [main] INFO com.niudada.Main -- 2025-07-04日🌆通知：
10:53:41.679 [main] INFO com.niudada.subject.Weather -- 广大市民请注意：近期天气将由*🌧️(☔️)*转为*⛅️*
10:53:41.679 [main] INFO com.niudada.observer.PhoneApp -- 手机收到天气更新，当前天气为：⛅️
10:53:41.679 [main] INFO com.niudada.observer.RadioStation -- 广播电台收到天气更新，当前天气为：⛅️
10:53:41.679 [main] INFO com.niudada.observer.SmartDevice -- 其他智能设备收到天气更新，当前天气为：⛅️

...
```

---

## 🧪 四、运行结果分析

每次调用`weather.timePasses()` 都会触发以下流程：

1. 天气发生变化；
2. `notifyObservers()` 方法被调用；
3. 所有注册的观察者依次执行`update()` 方法；
4. 各自输出不同的响应信息；

这体现了观察者模式的核心机制 —— **一对多的通知机制**

---

## 💡 五、观察者模式的应用场景


| 场景                     | 示例说明                             |
| ------------------------ | ------------------------------------ |
| UI组件监听事件           | 如按钮点击、输入框内容变更等         |
| 消息队列通知             | RabbitMQ、Kafka 中的发布/订阅机制    |
| 数据变化推送             | WebSocket 实现实时数据更新           |
| MVC架构中的视图更新      | Controller 改变 Model，View 自动刷新 |
| 游戏开发中的角色状态同步 | 玩家状态变化通知其他玩家或UI         |

---

## 📌 六、优点与缺点

### ✅ 优点

- 松耦合：主题与观察者之间通过接口通信，便于扩展
- 可维护性强：新增观察者只需实现接口，不影响原有逻辑
- 实时性好：状态变化后能立刻通知所有观察者

### ❌ 缺点

- 如果观察者数量过多，频繁通知可能影响性能
- 若观察者逻辑错误，可能导致主线程阻塞
- 不易调试，尤其在异步通知场景下

---

## 📚 七、总结

观察者模式是实现对象间**一对多通信**的经典方式；它适用于**状态变化需通知多个对象**的场景，如天气预警、消息推送、事件监听等；

通过之前的学习，你可以更清楚的知道：

- 如何定义主题与观察者；
- 如何建立一对多的通知机制；
- 如何将设计模式应用到实际业务中；

---

📌 **建议练习**：尝试添加新的观察者(如短信提醒服务),或让天气变化携带更多参数(如温度、湿度),进一步加深理解。
