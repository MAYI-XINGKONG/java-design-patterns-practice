package com.niudada.data_validate;

import lombok.Getter;

/**
 * 用户数据类
 */
public class UserData {

    @Getter
    private String userName;

    @Getter
    private String password;

    @Getter
    private String email;

    @Getter
    private int age;

    private boolean valid;

    @Getter
    private String validationMessage;

    public UserData(String userName, String password, String email, int age) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.age = age;
        this.valid = true;
    }

    public boolean isValid() {
        return valid;
    }

    public void setInvalid(String message) {
        this.valid = false;
        this.validationMessage = message;
    }



}
