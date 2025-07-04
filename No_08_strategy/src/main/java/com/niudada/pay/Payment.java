package com.niudada.pay;

import com.niudada.strategy.interfaces.PaymentStrategy;

public class Payment {
    private PaymentStrategy strategy;

    public Payment(PaymentStrategy strategy) {
        this.strategy = strategy;
    }

    public void changePaymentStrategy(PaymentStrategy strategy) {
        this.strategy = strategy;
    }

    public void toPay(int amount) {
        strategy.pay(amount);
    }
}
