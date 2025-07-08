package com.niudada.sku.shallow_copy;

import java.math.BigDecimal;

public abstract class ProductSku extends Prototype<ProductSku> {
    private final String id;
    private final String name;
    private final BigDecimal price;
    private final Integer stock;

    public ProductSku(String id, String name, BigDecimal price, Integer stock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "ProductSku{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                '}';
    }
}
