package com.niudada.request_filter;

/**
 * 请求过滤器抽象类
 */
public abstract class RequestFilter {
    protected RequestFilter nextFilter;

    protected abstract boolean processRequest(HttpRequest request);

    public void setNext(RequestFilter nextFilter) {
        this.nextFilter = nextFilter;
    }

    public void doFilter(HttpRequest request) {
        // 执行当前过滤器的处理逻辑
        boolean shouldContinue = processRequest(request);

        // 如果当前过滤器允许继续，且有下一个过滤器，则将请求传递给下一个过滤器
        if (shouldContinue && nextFilter != null) {
            nextFilter.doFilter(request);
        }
    }
}
