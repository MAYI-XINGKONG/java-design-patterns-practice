package com.niudada;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {
    public static void main(String[] args) {
        // 使用支付宝进行支付
        AlipayPayment alipayPayment = new AlipayPayment();
        alipayPayment.processPayment();
        // 使用微信进行支付
        WeChatPayPayment weChatPayPayment = new WeChatPayPayment();
        weChatPayPayment.processPayment();
    }
}