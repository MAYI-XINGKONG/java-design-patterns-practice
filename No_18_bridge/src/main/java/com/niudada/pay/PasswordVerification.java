package com.niudada.pay;

public class PasswordVerification implements PaymentVerification {
    @Override
    public void verify() {
        System.out.println("正在进行密码验证...");
        System.out.println("密码验证通过");
    }

    @Override
    public String getVerificationType() {
        return "密码验证";
    }
}
