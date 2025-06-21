package com.niudada.logger;

/**
 * 使用静态内部类实现单例
 */
public class Logger {

    /**
     * 私有构造函数,防止在其他地方人为创建实例
     */
    private Logger() {}

    /**
     * 静态内部类实现单例
     */
    private static class LoggerHolder {
        private static final Logger INSTANCE = new Logger();
    }

    /**
     * 获取单例实例(线程安全、来加载)
     */
    public static Logger getInstance() {
        return LoggerHolder.INSTANCE;
    }

    public void log(String message) {
        System.out.println("[LOG]: " + message);
    }
}
