package com.niudada.bank;

public class TransferCommand implements BankCommand {
    private Account fromAccount;
    private Account toAccount;
    private double amount;
    private boolean transferSuccessful;

    public TransferCommand(Account fromAccount, Account toAccount, double amount) {
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.amount = amount;
    }

    @Override
    public void execute() {
        System.out.println("执行转账：从 " + fromAccount.getAccountNumber() +
                " 向 " + toAccount.getAccountNumber() + " 转账 " + amount);
        if (fromAccount.getBalance() >= amount) {
            fromAccount.withdraw(amount);
            toAccount.deposit(amount);
            transferSuccessful = true;
        } else {
            System.out.println("转账失败：账户 " + fromAccount.getAccountNumber() + " 余额不足");
            transferSuccessful = false;
        }
    }

    @Override
    public void undo() {
        if (transferSuccessful) {
            System.out.println("撤销转账：从 " + toAccount.getAccountNumber() +
                    " 向 " + fromAccount.getAccountNumber() + " 退回 " + amount);
            toAccount.withdraw(amount);
            fromAccount.deposit(amount);
        } else {
            System.out.println("无需退回：原转账未成功执行");
        }
    }
}
