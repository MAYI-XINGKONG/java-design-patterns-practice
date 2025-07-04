package com.niudada.strategy;

import com.niudada.strategy.interfaces.PaymentStrategy;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AliPayStrategy implements PaymentStrategy {
    @Override
    public void pay(int amount) {
        log.info("叮！支付宝支付 {} 元", amount / 100.0);
    }
}
