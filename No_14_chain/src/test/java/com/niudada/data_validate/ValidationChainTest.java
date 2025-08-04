package com.niudada.data_validate;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class ValidationChainTest {

    @Test
    public void test() {
        // 构建验证链: Username -> Email -> Age -> Password
        Validator userNameValidator = new UsernameValidator();
        Validator passwordValidator = new PasswordValidator();
        Validator emailValidator = new EmailValidator();
        Validator ageValidator = new AgeValidator();

        userNameValidator.setNext(passwordValidator);
        passwordValidator.setNext(emailValidator);
        emailValidator.setNext(ageValidator);

        // 测试
        log.info("用户数据验证演示：");
        log.info("--- 测试有效用户数据 ---");
        UserData validUser = new UserData("niudada", "123456", "niudada@163.com", 18);
        if (userNameValidator.validate(validUser)) {
            System.out.println("用户数据验证通过");
        } else {
            System.out.println("验证失败：" + validUser.getValidationMessage());
        }

        log.info("--- 测试无效无效邮箱 ---");
        UserData invalidEmailUser = new UserData("niudd", "123456", "invalid-email", 30);
        if (emailValidator.validate(invalidEmailUser)) {
            System.out.println("用户数据验证通过");
        } else {
            System.out.println("验证失败：" + invalidEmailUser.getValidationMessage());
        }

        log.info("--- 测试无效年龄 ---");
        UserData invalidAgeUser = new UserData("niudada", "123456", "niudada@gmail.com", 10);
        if (ageValidator.validate(invalidAgeUser)) {
            System.out.println("用户数据验证通过");
        } else {
            System.out.println("验证失败：" + invalidAgeUser.getValidationMessage());
        }

    }
}
