package com.niudada.request_filter;

public class AuthorizationFilter extends RequestFilter {
    @Override
    protected boolean processRequest(HttpRequest request) {
        System.out.println("检查请求授权...");

        // 只有认证通过的请求才能到这里进行授权检查
        if (request.isAuthenticated()) {
            // 模拟授权逻辑
            if ("GET".equals(request.getMethod()) && "valid_token".equals(request.getAuthToken())) {
                request.setAuthorized(true);
                System.out.println("授权成功!");
                return true;
            } else {
                System.out.println("授权失败,拒绝请求!");
                request.setInvalid();
                return false;
            }
        }

        System.out.println("未认证的请求,拒绝!");
        request.setInvalid();
        return false;
    }
}
