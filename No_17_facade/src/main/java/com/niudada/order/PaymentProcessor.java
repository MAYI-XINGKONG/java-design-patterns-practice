package com.niudada.order;

public class PaymentProcessor extends OrderProcessor {
    @Override
    public void process(String orderId) {
        System.out.println("开始处理订单 " + orderId + " 的支付...");
        System.out.println("支付成功");
    }
}
