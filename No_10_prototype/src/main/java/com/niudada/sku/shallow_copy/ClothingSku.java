package com.niudada.sku.shallow_copy;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

public class ClothingSku extends ProductSku {

    private String size;

    private String color;

    @Getter
    @Setter
    private Address address;

    public ClothingSku(String id, String name, BigDecimal price, Integer stock, String size, String color, Address address) {
        super(id, name, price, stock);
        this.size = size;
        this.color = color;
        this.address = address;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return super.toString() + ", size='" + size + "', color='" + color + "', address=" + address + "'";
    }
}
