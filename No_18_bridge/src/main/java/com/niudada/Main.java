package com.niudada;

import com.niudada.pay.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {
    public static void main(String[] args) {
        log.info("=== 桥接模式示例：支付方式与验证方式 ===");

        // 创建不同的验证方式
        PaymentVerification faceRecognition = new FaceRecognition();
        PaymentVerification fingerprint = new Fingerprint();
        PaymentVerification passwordVerification = new PasswordVerification();

        // 创建不同的支付方式,并桥接不同的验证方式
        PaymentMethod weChatWithFace = new WeChatPay(faceRecognition);
        PaymentMethod alipayWithFingerprint = new Alipay(fingerprint);
        PaymentMethod weChatWithPassword = new WeChatPay(passwordVerification);
        PaymentMethod alipayWithFace = new Alipay(faceRecognition);

        // 执行支付
        log.info("微信 + 人脸验证 -> 支付");
        weChatWithFace.pay(100.0);
        log.info("支付宝 + 指纹验证 -> 支付");
        alipayWithFingerprint.pay(200.0);
        log.info("微信 + 密码验证 -> 支付");
        weChatWithPassword.pay(300.0);
        log.info("支付宝 + 人脸验证 -> 支付");
        alipayWithFace.pay(400.0);
    }
}