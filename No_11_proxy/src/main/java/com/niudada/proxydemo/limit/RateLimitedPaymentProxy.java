package com.niudada.proxydemo.limit;

import java.time.Instant;
import java.util.concurrent.atomic.AtomicInteger;

public class RateLimitedPaymentProxy implements PaymentService{
    private final PaymentService realService;

    /**
     * 阈值为3次/秒
     */
    private static final int MAX_REQUESTS_PER_SECOND = 3;

    /**
     * 记录请求数
     */
    private final AtomicInteger requestCount = new AtomicInteger(0);

    /**
     * 起始时间(秒)
     */
    private long lastRequestTime = Instant.now().getEpochSecond();

    public RateLimitedPaymentProxy(PaymentService realService) {
        this.realService = realService;
    }

    @Override
    public void processPayment(double amount) {
        long currentTime = Instant.now().getEpochSecond();
        // 如果超过1秒，则重置请求数并更新时间戳
        if (currentTime - lastRequestTime > 1) {
            requestCount.set(0);
            lastRequestTime = currentTime;
        }

        if (requestCount.get() < MAX_REQUESTS_PER_SECOND) {
            // 如果请求数未达到阈值则放行并累加请求数
            realService.processPayment(amount);
            requestCount.incrementAndGet();
        } else {
            // 否则拒绝
            System.out.println("请求过于频繁，请稍后再试!");
        }
    }
}
