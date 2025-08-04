package com.niudada.request_filter;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class FilterChainTest {

    @Test
    public void test() {
        // 构建过滤器链: Logging -> Authentication -> Authorization -> Security
        RequestFilter loggingFilter = new LoggingFilter();
        RequestFilter authenticationFilter = new AuthenticationFilter();
        RequestFilter authorizationFilter = new AuthorizationFilter();
        RequestFilter securityFilter = new SecurityFilter();

        loggingFilter.setNext(authenticationFilter);
        authenticationFilter.setNext(authorizationFilter);
        authorizationFilter.setNext(securityFilter);

        log.info("--- 测试合法请求 ---");
        HttpRequest validRequest = new HttpRequest("api/users", "GET", "valid_token");
        loggingFilter.doFilter(validRequest);

        log.info("--- 测试未认证请求 ---");
        HttpRequest unauthenticatedRequest = new HttpRequest("api/users", "GET", "invalid_token");
        loggingFilter.doFilter(unauthenticatedRequest);

        log.info("--- 测试未授权请求 ---");
        HttpRequest unauthorizedRequest = new HttpRequest("api/users", "POST", "valid_token");
        loggingFilter.doFilter(unauthorizedRequest);

        log.info("--- 测试安全威胁请求 ---");
        HttpRequest securityThreatRequest = new HttpRequest("admin/rm-all", "GET", "valid_token");
        loggingFilter.doFilter(securityThreatRequest);
    }
}
