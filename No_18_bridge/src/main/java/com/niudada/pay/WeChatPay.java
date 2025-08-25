package com.niudada.pay;

public class WeChatPay extends PaymentMethod {

    public WeChatPay(PaymentVerification verification) {
        super(verification);
    }

    @Override
    public void pay(double amount) {
        System.out.println("使用微信支付:");
        verification.verify();
        System.out.println("微信支付成功，金额: " + amount + "元");
    }
}
