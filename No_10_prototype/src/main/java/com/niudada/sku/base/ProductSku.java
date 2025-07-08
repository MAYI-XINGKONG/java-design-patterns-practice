package com.niudada.sku.base;

import java.math.BigDecimal;

public abstract class ProductSku implements Prototype<ProductSku> {
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

    public ProductSku(ProductSku source) {
        this.id = source.id;
        this.name = source.name;
        this.price = source.price;
        this.stock = source.stock;
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
