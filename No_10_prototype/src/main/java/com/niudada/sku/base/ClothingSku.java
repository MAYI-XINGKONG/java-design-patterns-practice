package com.niudada.sku.base;

import java.math.BigDecimal;

public class ClothingSku extends ProductSku {
    private String size;
    private String color;

    public ClothingSku(String id, String name, BigDecimal price, Integer stock, String size, String color) {
        super(id, name, price, stock);
        this.size = size;
        this.color = color;
    }

    public ClothingSku(ClothingSku source) {
        super(source);
        this.size = source.size;
        this.color = source.color;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public ProductSku copy() {
        return new ClothingSku(this);
    }

    @Override
    public String toString() {
        return super.toString() + ", size='" + size + "', color='" + color + "'";
    }
}
