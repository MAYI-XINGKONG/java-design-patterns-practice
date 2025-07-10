package com.niudada.proxydemo.auth;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class AuthTest {

    @Test
    public void test() {
        ResourceService realService = new SensitiveResourceService();
        ResourceService proxy = new SecureResourceProxy(realService);

        log.info("ADMIN访问...");
        proxy.accessResource("ADMIN");
        log.info("普通用户访问...");
        proxy.accessResource("USER");
    }
}
