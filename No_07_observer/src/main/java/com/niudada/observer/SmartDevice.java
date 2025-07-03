package com.niudada.observer;

import com.niudada.enums.WeatherType;
import com.niudada.observer.interfaces.WeatherObserver;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SmartDevice implements WeatherObserver {
    @Override
    public void update(WeatherType currentWeather) {
        log.info("其他智能设备收到天气更新，当前天气为：{}", currentWeather.getDescription());
    }
}
