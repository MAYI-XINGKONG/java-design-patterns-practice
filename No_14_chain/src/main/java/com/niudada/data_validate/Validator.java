package com.niudada.data_validate;

/**
 * 抽象验证器类
 */
public abstract class Validator {
    protected Validator nextValidator;

    protected abstract boolean doValidate(UserData userData);

    public void setNext(Validator next) {
        this.nextValidator = next;
    }

    public boolean validate(UserData userData) {
        // 执行当前验证器的验证逻辑
        boolean currentValid = doValidate(userData);

        // 如果当前验证失败，直接返回false
        if (!currentValid) {
            return false;
        }

        // 如果当前验证通过且有下一个验证器，继续验证
        if (nextValidator != null) {
            return nextValidator.validate(userData);
        }

        // 如果没有下一个验证器且当前验证器通过，返回true
        return true;
    }
}
