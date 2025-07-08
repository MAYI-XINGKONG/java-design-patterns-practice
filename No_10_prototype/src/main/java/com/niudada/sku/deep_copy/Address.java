package com.niudada.sku.deep_copy;

import lombok.Data;

import java.io.Serializable;

@Data
public class Address implements Serializable {
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
