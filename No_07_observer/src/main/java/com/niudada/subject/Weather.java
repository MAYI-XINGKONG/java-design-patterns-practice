package com.niudada.subject;

import com.niudada.enums.WeatherType;
import com.niudada.observer.interfaces.WeatherObserver;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

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
