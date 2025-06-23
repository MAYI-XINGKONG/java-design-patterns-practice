package com.niudada;

public class Main {
    public static void main(String[] args) {
        // 和注解@Accessors(chain = true)有些相似，区别在笔记中进行了介绍
        SmartWatchHealthConfig build = new SmartWatchHealthConfig.Builder("XXX Watch Pro")
                .setHeartRateMonitoring("实时")
                .setBloodOxygenMonitoring("每小时一次")
                .enableSleepTracking(true)
                .enableStepCounting(true)
                .enableStressDetection(true)
                .enableGpsTracking(true)
                .setDataSyncFrequency(5)
                .enableHealthAlerts(true)
                .build();
        System.out.println(build);
    }
}