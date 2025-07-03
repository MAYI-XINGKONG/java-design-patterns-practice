package com.niudada.observer.interfaces;

import com.niudada.enums.WeatherType;

public interface WeatherObserver {
    void update(WeatherType currentWeather);
}
