package com.niudada.proxydemo.remote;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RemoteWeatherProxy implements WeatherService{
    private final WeatherService realService;

    public RemoteWeatherProxy() {
        log.info("正在连接远程天气服务中心...");
        this.realService = new RemoteWeatherService();
    }

    @Override
    public String getWeather(String city) {
        log.info("[Proxy] 正在请求天气信息: {}", city);
        return realService.getWeather(city);
    }
}
