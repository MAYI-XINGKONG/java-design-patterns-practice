package com.niudada.data_validate;

/**
 * 密码校验器
 */
public class PasswordValidator extends Validator {
    @Override
    protected boolean doValidate(UserData userData) {
        if (userData.getPassword() == null || userData.getPassword().length() < 6) {
            userData.setInvalid("密码长度不能少于 6 个字符");
            return false;
        }
        return true;
    }
}
