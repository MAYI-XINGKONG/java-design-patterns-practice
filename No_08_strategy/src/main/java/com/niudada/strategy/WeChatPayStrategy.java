package com.niudada.strategy;

import com.niudada.strategy.interfaces.PaymentStrategy;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WeChatPayStrategy implements PaymentStrategy {
    @Override
    public void pay(int amount) {
        log.info("叮！微信支付 {} 元", amount / 100.0);
    }
}
