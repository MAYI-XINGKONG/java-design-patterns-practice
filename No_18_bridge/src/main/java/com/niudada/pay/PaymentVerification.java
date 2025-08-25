package com.niudada.pay;

/**
 * 实现部分：支付验证方式接口
 * 属于是桥接模式中的`实现`
 */
public interface PaymentVerification {
    void verify();
    String getVerificationType();
}
