package com.niudada;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class OrderPaymentTemplate {

    /**
     * 通用方法
     */
    private void validateOrder() {
        log.info("验证订单信息...");
    }
    private void recordpayment() {
        log.info("记录支付结果...");
    }
    private void sendNotification() {
        log.info("发送支付结果通知...\n");
    }
    /**
     * 抽象方法,具体实现延迟到子类
     */
    protected abstract void performPayment();

    /**
     * 模板方法,定义支付流程骨架
     */
    public final void processPayment() {
        validateOrder();
        performPayment();
        recordpayment();
        sendNotification();
    }

}
