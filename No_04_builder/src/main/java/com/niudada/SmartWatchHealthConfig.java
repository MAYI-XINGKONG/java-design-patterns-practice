package com.niudada;

/**
 * 这里没有使用注解
 */
public final class SmartWatchHealthConfig {

    /**
     * 设备型号
     */
    private final String deviceModel;

    /**
     * 心率监测频率(实时/每小时/关闭)
     */
    private final String heartRateMonitoring;

    /**
     * 血氧检测频率
     */
    private final String bloodOxygenMonitoring;

    /**
     * 睡眠追踪是否开启
     */
    private final boolean sleepTrackingEnabled;

    /**
     * 步数统计是否开启
     */
    private final boolean stepCountingEnabled;

    /**
     * 是否启用压力检测
     */
    private final boolean stressDetectionEnabled;

    /**
     * 是否记录运动轨迹(GPS)
     */
    private final boolean gpsTrackingEnabled;

    /**
     * 数据同步频率、单位：分钟，默认5分钟
     */
    private final int dataSyncFrequency;

    /**
     * 是否推送健康预警
     */
    private final boolean healthAlertsEnabled;

    // 私有构造方法，由Builder调用
    private SmartWatchHealthConfig(Builder builder) {
        this.deviceModel = builder.deviceModel;
        this.heartRateMonitoring = builder.heartRateMonitoring;
        this.bloodOxygenMonitoring = builder.bloodOxygenMonitoring;
        this.sleepTrackingEnabled = builder.sleepTrackingEnabled;
        this.stepCountingEnabled = builder.stepCountingEnabled;
        this.stressDetectionEnabled = builder.stressDetectionEnabled;
        this.gpsTrackingEnabled = builder.gpsTrackingEnabled;
        this.dataSyncFrequency = builder.dataSyncFrequency;
        this.healthAlertsEnabled = builder.healthAlertsEnabled;
    }

    @Override
    public String toString() {
        return "SmartWatchHealthConfig{" +
                "deviceModel='" + deviceModel + '\'' +
                ", heartRateMonitoring='" + heartRateMonitoring + '\'' +
                ", bloodOxygenMonitoring='" + bloodOxygenMonitoring + '\'' +
                ", sleepTrackingEnabled=" + sleepTrackingEnabled +
                ", stepCountingEnabled=" + stepCountingEnabled +
                ", stressDetectionEnabled=" + stressDetectionEnabled +
                ", gpsTrackingEnabled=" + gpsTrackingEnabled +
                ", dataSyncFrequency=" + dataSyncFrequency +
                ", healthAlertsEnabled=" + healthAlertsEnabled +
                '}';
    }

    public static class Builder {
        private final String deviceModel;

        /**
         *心率监测频率(实时/每小时/关闭)、默认关闭
         */
        private String heartRateMonitoring = "off";

        /**
         * 血氧检测频率、默认每小时一次
         */
        private String bloodOxygenMonitoring = "hourly";

        /**
         * 睡眠追踪是否开启
         */
        private boolean sleepTrackingEnabled = true;

        /**
         * 步数统计是否开启
         */
        private boolean stepCountingEnabled = true;

        /**
         * 是否启用压力检测
         */
        private boolean stressDetectionEnabled = false;

        /**
         * 是否记录运动轨迹(GPS)
         */
        private boolean gpsTrackingEnabled = false;

        /**
         * 数据同步频率、单位：分钟，默认5分钟
         */
        private int dataSyncFrequency = 5;

        /**
         * 是否推送健康预警
         */
        private boolean healthAlertsEnabled = true;

        public Builder(String deviceModel) {
            if (deviceModel == null || deviceModel.isBlank()) {
                throw new IllegalArgumentException("设备型号不能为空");
            }
            this.deviceModel = deviceModel;
        }

        /**
         * 心率监测频率设置
         */
        public Builder setHeartRateMonitoring(String frequency) {
            this.heartRateMonitoring = frequency != null ? frequency : "off";
            return this;
        }

        /**
         * 血氧监测频率设置
         */
        public Builder setBloodOxygenMonitoring(String frequency) {
            this.bloodOxygenMonitoring = frequency != null ? frequency : "off";
            return this;
        }

        /**
         * 睡眠追踪开关
         */
        public Builder enableSleepTracking(boolean enabled) {
            this.sleepTrackingEnabled = enabled;
            return this;
        }

        /**
         * 步数统计开关
         */
        public Builder enableStepCounting(boolean enabled) {
            this.stepCountingEnabled = enabled;
            return this;
        }

        /**
         * 压力检测开关
         */
        public Builder enableStressDetection(boolean enabled) {
            this.stressDetectionEnabled = enabled;
            return this;
        }

        /**
         * GPS轨迹记录开关
         */
        public Builder enableGpsTracking(boolean enabled) {
            this.gpsTrackingEnabled = enabled;
            return this;
        }

        /**
         * 数据同步频率（单位：分钟）
         */
        public Builder setDataSyncFrequency(int minutes) {
            if (minutes < 0) {
                throw new IllegalArgumentException("数据同步频率不能为负数");
            }
            this.dataSyncFrequency = minutes;
            return this;
        }

        /**
         * 是否启用健康提醒
         */
        public Builder enableHealthAlerts(boolean enabled) {
            this.healthAlertsEnabled = enabled;
            return this;
        }

        /**
         * 构建最终对象
         */
        public SmartWatchHealthConfig build() {
            return new SmartWatchHealthConfig(this);
        }
    }
}
