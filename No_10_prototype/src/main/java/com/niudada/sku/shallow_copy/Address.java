package com.niudada.sku.shallow_copy;

import lombok.Data;

/**
 * 用于测试浅拷贝和深拷贝两种方式的区别(当前是浅拷贝)
 */
@Data
public class Address {
    private String city;
    private String street;

    public Address(String city, String street) {
        this.city = city;
        this.street = street;
    }

    @Override
    public String toString() {
        return "Address{" +
                "city='" + city + '\'' +
                ", street='" + street + '\'' +
                '}';
    }
}
