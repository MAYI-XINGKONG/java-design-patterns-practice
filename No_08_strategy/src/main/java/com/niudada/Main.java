package com.niudada;

import com.niudada.pay.Payment;
import com.niudada.strategy.AliPayStrategy;
import com.niudada.strategy.BankCardStrategy;
import com.niudada.strategy.WeChatPayStrategy;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {
    public static void main(String[] args) {
        log.info("你好，总共消费1266元，请扫码或刷卡支付...");
        int amount = 126600; // 分(此处金额使用最小的货币单位)
        log.info("使用微信支付：");
        Payment payment = new Payment(new WeChatPayStrategy());
        payment.toPay(100000);
        log.info("微信余额不足！使用支付宝支付：");
        payment.changePaymentStrategy(new AliPayStrategy());
        payment.toPay(20000);
        log.info("支付宝余额不足！刷卡支付：");
        payment.changePaymentStrategy(new BankCardStrategy());
        payment.toPay(6600);
        log.info("信息来了！信用卡已冻结！请尽快还款解冻...");
        log.info("这是个悲伤的故事😭...");
    }
}