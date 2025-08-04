package com.niudada.request_filter;

public class LoggingFilter extends RequestFilter {
    @Override
    protected boolean processRequest(HttpRequest request) {
        System.out.println("记录请求日志 - " + request.getMethod() + " " + request.getUrl());
        // 一直允许继续
        return true;
    }
}
