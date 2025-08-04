package com.niudada.data_validate;

/**
 * 年龄验证器
 */
public class AgeValidator extends Validator {
    @Override
    protected boolean doValidate(UserData userData) {
        if (userData.getAge() < 18 || userData.getAge() > 150) {
            userData.setInvalid("年龄必须在 18 到 150 之间");
            return false;
        }
        return true;
    }
}
