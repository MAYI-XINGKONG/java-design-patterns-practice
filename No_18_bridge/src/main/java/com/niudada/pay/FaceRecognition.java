package com.niudada.pay;

public class FaceRecognition implements PaymentVerification {
    @Override
    public void verify() {
        System.out.println("正在进行人脸识别验证...");
        System.out.println("人脸识别验证通过");
    }

    @Override
    public String getVerificationType() {
        return "人脸识别";
    }
}
