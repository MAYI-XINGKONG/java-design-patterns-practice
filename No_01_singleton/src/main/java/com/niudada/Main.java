package com.niudada;

import com.niudada.config.GlobalConfig;
import com.niudada.logger.Logger;

public class Main {
    public static void main(String[] args) {
        Logger logger = Logger.getInstance();
        logger.log("application start...");
        GlobalConfig config = GlobalConfig.getInstance();
        config.print();
        logger.log("application change config...");
        config.setName("myName");
        config.setMaxConnections(100);
        config.print();

        // 校验两个单例是否是同一个实例
        logger.log("Are two instances the same?");
        Logger _logger = Logger.getInstance();
        GlobalConfig _config = GlobalConfig.getInstance();
        logger.log("Logger: " + (logger == _logger));
        logger.log("GlobalConfig: " + (config == _config));
    }
}