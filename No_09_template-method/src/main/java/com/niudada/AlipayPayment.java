package com.niudada;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AlipayPayment extends OrderPaymentTemplate{
    @Override
    protected void performPayment() {
        log.info("使用支付宝进行支付...");
    }
}
