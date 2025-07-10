package com.niudada.proxydemo.log;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class LogTest {

    @Test
    public void testLog() {
        LoginService proxy = new LoggingLoginProxy(new RealLoginService());
        log.info("尝试登录...");
        proxy.login("admin", "admin");
        log.info("再次登录...");
        proxy.login("admin", "123456");
    }
}
