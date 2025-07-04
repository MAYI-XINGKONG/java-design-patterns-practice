package com.niudada.strategy;

import com.niudada.strategy.interfaces.PaymentStrategy;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BankCardStrategy implements PaymentStrategy {
    @Override
    public void pay(int amount) {
        log.info("信息来了！信用卡支付 {} 元", amount / 100.0);
    }
}
