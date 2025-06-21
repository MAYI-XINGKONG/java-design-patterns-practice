package com.niudada.config;

import lombok.Getter;
import lombok.Setter;

/**
 * 使用双检锁(双重检查锁定)实现线程安全的单例模式
 */
@Getter
@Setter
public class GlobalConfig {

    /**
     * volatile 该关键字会禁止线程缓存，从而保证多线程环境下的可见性和有序性
     */
    private static volatile GlobalConfig instance;

    /**
     * 配置项-名称
     */
    private String name;

    /**
     * 配置项-最大连接数
     */
    private Integer maxConnections;

    /**
     * 私有构造函数,防止在其他地方人为创建实例
     */
    private GlobalConfig() {
        this.name = "defaltName";
        this.maxConnections = 10;
    }

    /**
     * 获取单例实例，并保证线程安全
     */
    public static GlobalConfig getInstance() {
        if (instance == null) {
            synchronized (GlobalConfig.class) {
                if (instance == null) {
                    instance = new GlobalConfig();
                }
            }
        }
        return instance;
    }

    public void print() {
        System.out.println("name:" + name);
        System.out.println("maxConnections:" + maxConnections);
    }
}
