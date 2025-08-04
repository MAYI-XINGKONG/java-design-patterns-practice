package com.niudada.bank;

import lombok.Getter;

public class Account {

    @Getter
    private String accountNumber;

    @Getter
    private double balance;

    public Account(String accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public void deposit(double amount) {
        balance += amount;
        System.out.println("账户 " + accountNumber + " 存入 " + amount + ",余额：" + balance);
    }

    public void withdraw(double amount) {
        if (amount > balance) {
            System.out.println("账户 " + accountNumber + " 提款 " + amount + " 失败，余额不足");
        } else {
            balance -= amount;
            System.out.println("账户 " + accountNumber + " 提款 " + amount + ",余额：" + balance);
        }
    }
}
