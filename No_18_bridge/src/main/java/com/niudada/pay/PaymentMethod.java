package com.niudada.pay;

/**
 * 抽象部分：支付方式/平台
 * 属于是桥接模式中的`抽象`
 */
public abstract class PaymentMethod {
    protected PaymentVerification verification;

    public PaymentMethod(PaymentVerification verification) {
        this.verification = verification;
    }

    public abstract void pay(double amount);

    public PaymentVerification getVerification() {
        return verification;
    }
}
