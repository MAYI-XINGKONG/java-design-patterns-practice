package com.niudada.proxydemo.limit;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RealPaymentService implements PaymentService{

    @Override
    public void processPayment(double amount) {
        log.info("支付成功: ￥{}", amount);
    }
}
