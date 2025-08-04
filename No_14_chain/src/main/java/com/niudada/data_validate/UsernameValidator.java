package com.niudada.data_validate;

/**
 * 验证用户名
 */
public class UsernameValidator extends Validator {
    @Override
    protected boolean doValidate(UserData userData) {
        if (userData.getUserName() == null || userData.getUserName().length() < 3) {
            userData.setInvalid("用户名长度不能少于 3 个字符");
            return false;
        }
        return true;
    }
}
