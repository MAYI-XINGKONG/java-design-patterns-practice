package com.niudada.proxydemo.log;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RealLoginService implements LoginService{
    @Override
    public boolean login(String username, String password) {
        if ("admin".equals(username) && "123456".equals(password)) {
            log.info("用户[{}]登录成功!", username);
            return true;
        } else {
            log.info("用户[{}]登录失败!", username);
            return false;
        }
    }
}
