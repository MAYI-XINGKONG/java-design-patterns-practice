package com.niudada.bank;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class BankTest {

    @Test
    public void test() {
        log.info("银行转账命令模式演示:");

        Account csAccount = new Account("财神", 9999999.00);
        Account wkAccount = new Account("孙悟空", 2.00);

        log.info("转账前：");
        log.info("{} - 账户余额：{}",csAccount.getAccountNumber(), csAccount.getBalance());
        log.info("{} - 账户余额：{}",csAccount.getAccountNumber(), wkAccount.getBalance());

        BankInvoker invoker = new BankInvoker();

        // 正常转账
        log.info("--- 正常转账 ---");
        BankCommand transfer1 = new TransferCommand(csAccount, wkAccount, 10000.00);
        invoker.executeCommand(transfer1);

        log.info("转账后：");
        log.info("{} - 账户余额：{}",csAccount.getAccountNumber(), csAccount.getBalance());
        log.info("{} - 账户余额：{}",csAccount.getAccountNumber(), wkAccount.getBalance());

        // 撤销转账
        log.info("--- 撤销转账 ---");
        invoker.undoLastCommand();

        log.info("撤销后：");
        log.info("{} - 账户余额：{}",csAccount.getAccountNumber(), csAccount.getBalance());
        log.info("{} - 账户余额：{}",csAccount.getAccountNumber(), wkAccount.getBalance());

        // 转账超过余额时
        log.info("--- 转账超过余额时 ---");
        BankCommand transfer2 = new TransferCommand(wkAccount, csAccount, 10.00);
        invoker.executeCommand(transfer2);

        log.info("--- 撤销转账失败时 ---");
        invoker.undoLastCommand();
    }
}
