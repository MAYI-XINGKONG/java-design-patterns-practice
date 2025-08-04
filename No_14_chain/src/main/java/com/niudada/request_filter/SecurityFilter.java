package com.niudada.request_filter;

public class SecurityFilter extends RequestFilter {
    @Override
    protected boolean processRequest(HttpRequest request) {
        System.out.println("检查安全威胁...");

        // 模拟安全检查
        if (request.getUrl().contains("rm") && "valid_token".equals(request.getAuthToken())) {
            System.out.println("检测到安全威胁,拒绝请求!");
            request.setInvalid();
            return false;
        }

        System.out.println("安全检查通过!");
        return true;
    }
}
