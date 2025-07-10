package com.niudada.proxydemo.remote;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class RemoteTest {
    @Test
    public void testRemote() {
        WeatherService weatherService = new RemoteWeatherProxy();
        log.info("获取北京天气...");
        log.info(weatherService.getWeather("Beijing"));
        log.info("获取广州天气...");
        log.info(weatherService.getWeather("Guangzhou"));
    }
}
