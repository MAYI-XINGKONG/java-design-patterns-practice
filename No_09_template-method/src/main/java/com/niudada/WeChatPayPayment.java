package com.niudada;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WeChatPayPayment extends OrderPaymentTemplate{
    @Override
    protected void performPayment() {
        log.info("使用微信进行支付...");
    }
}
