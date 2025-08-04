package com.niudada.request_filter;

public class AuthenticationFilter extends RequestFilter {
    @Override
    protected boolean processRequest(HttpRequest request) {
        System.out.println("验证请求认证...");

        // 模拟认证逻辑
        if ("valid_token".equals(request.getAuthToken())) {
            request.setAuthenticated(true);
            System.out.println("验证成功!");
            return true;
        } else {
            System.out.println("认证失败,拒绝请求!");
            request.setInvalid();
            return false;
        }
    }
}
