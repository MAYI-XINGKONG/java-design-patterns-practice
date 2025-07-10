package com.niudada.proxydemo.auth;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SecureResourceProxy implements ResourceService{
    private final ResourceService realService;
    private static final String ADMIN_ROLE = "ADMIN";

    public SecureResourceProxy(ResourceService realService) {
        this.realService = realService;
    }

    @Override
    public void accessResource(String userRole) {
        if (ADMIN_ROLE.equals(userRole)) {
            realService.accessResource(userRole);
        } else {
            log.info("[{}] 无权限访问该资源!", userRole);
        }
    }
}
