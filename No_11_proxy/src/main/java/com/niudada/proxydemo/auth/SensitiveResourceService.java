package com.niudada.proxydemo.auth;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SensitiveResourceService implements ResourceService{
    @Override
    public void accessResource(String userRole) {
        log.info("敏感资源被 [{}] 成功访问!", userRole);
    }
}
