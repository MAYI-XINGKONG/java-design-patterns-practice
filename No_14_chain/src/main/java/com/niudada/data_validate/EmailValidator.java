package com.niudada.data_validate;

/**
 * 邮箱验证器
 */
public class EmailValidator extends Validator {
    @Override
    protected boolean doValidate(UserData userData) {
        if (userData.getEmail() == null || !userData.getEmail().contains("@")) {
            userData.setInvalid("邮箱格式不正确");
            return false;
        }
        return true;
    }
}
