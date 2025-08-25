package com.niudada.pay;

public class Fingerprint implements PaymentVerification {
    @Override
    public void verify() {
        System.out.println("正在进行指纹识别验证...");
        System.out.println("指纹识别验证通过");
    }

    @Override
    public String getVerificationType() {
        return "指纹识别";
    }
}
