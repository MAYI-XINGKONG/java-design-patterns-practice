package com.niudada.proxydemo.log;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

@Slf4j
public class LoggingLoginProxy implements LoginService{
    private final LoginService realService;
    public LoggingLoginProxy(LoginService realService) {
        this.realService = realService;
    }
    @Override
    public boolean login(String username, String password) {
        log.info("[" + LocalDateTime.now() + "] 尝试登录: 用户名=" + username);
        boolean result = realService.login(username, password);
        log.info("[" + LocalDateTime.now() + "] 登录结果: " + (result ? "成功" : "失败"));
        return result;
    }
}
