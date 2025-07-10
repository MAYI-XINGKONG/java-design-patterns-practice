package com.niudada.proxydemo.remote;

public class RemoteWeatherService implements WeatherService {
    @Override
    public String getWeather(String city) {
        // 模拟远程调用耗时
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return switch (city.toLowerCase()){
            case "beijing" -> "北京: 晴，25°C";
            case "shanghai" -> "上海: 多云，28°C";
            case "guangzhou" -> "广州: 雷阵雨，32°C";
            default -> "未知城市或暂无数据";
        };
    }
}
