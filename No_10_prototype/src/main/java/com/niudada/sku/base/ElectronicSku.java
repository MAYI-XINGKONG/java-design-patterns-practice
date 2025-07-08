package com.niudada.sku.base;

import java.math.BigDecimal;

public class ElectronicSku extends ProductSku {
    private String model;
    private int warrantyPeriod;

    public ElectronicSku(String id, String name, BigDecimal price, Integer stock, String model, int warrantyPeriod) {
        super(id, name, price, stock);
        this.model = model;
        this.warrantyPeriod = warrantyPeriod;
    }

    public ElectronicSku(ElectronicSku source) {
        super(source);
        this.model = source.model;
        this.warrantyPeriod = source.warrantyPeriod;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setWarrantyPeriod(int warrantyPeriod) {
        this.warrantyPeriod = warrantyPeriod;
    }

    @Override
    public ProductSku copy() {
        return new ElectronicSku(this);
    }

    @Override
    public String toString() {
        return super.toString() + ", model='" + model + "', warrantyPeriod=" + warrantyPeriod;
    }
}
