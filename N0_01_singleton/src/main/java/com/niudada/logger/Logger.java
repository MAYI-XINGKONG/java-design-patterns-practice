package com.niudada.logger;

public class Logger {
    private static final Logger instance = new Logger();
    private Logger() {}
    public static Logger getInstance() {
        return instance;
    }
    public void log(String message) {
        System.out.println("Logger: " + message);
    }
}
