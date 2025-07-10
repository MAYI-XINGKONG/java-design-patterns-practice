package com.niudada.proxydemo.limit;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

@Slf4j
public class LimitTest {

    @Test
    public void test() {
        PaymentService realService = new RealPaymentService();
        PaymentService proxy = new RateLimitedPaymentProxy(realService);

        for (int i = 1; i <= 5; i++) {
            try {
                TimeUnit.MILLISECONDS.sleep(200);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            log.info("请求 #{}：", i);
            proxy.processPayment(100.0);
        }
    }
}
