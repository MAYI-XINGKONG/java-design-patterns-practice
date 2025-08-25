package com.niudada.pay;

public class Alipay extends PaymentMethod {

    public Alipay(PaymentVerification verification) {
        super(verification);
    }

    @Override
    public void pay(double amount) {
        System.out.println("使用支付宝支付:");
        verification.verify();
        System.out.println("支付宝支付成功，金额: " + amount + "元");
    }
}
