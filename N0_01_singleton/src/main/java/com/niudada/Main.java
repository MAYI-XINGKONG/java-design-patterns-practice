package com.niudada;

import com.niudada.logger.Logger;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {
    public static void main(String[] args) {
        Logger logger = Logger.getInstance();
        logger.log("Hello World");
    }
}