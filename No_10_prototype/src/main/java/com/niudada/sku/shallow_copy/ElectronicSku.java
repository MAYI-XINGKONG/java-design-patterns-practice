package com.niudada.sku.shallow_copy;

import java.math.BigDecimal;

public class ElectronicSku extends ProductSku {
    private String model;
    private int warrantyPeriod;

    public ElectronicSku(String id, String name, BigDecimal price, Integer stock, String model, int warrantyPeriod) {
        super(id, name, price, stock);
        this.model = model;
        this.warrantyPeriod = warrantyPeriod;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setWarrantyPeriod(int warrantyPeriod) {
        this.warrantyPeriod = warrantyPeriod;
    }

    @Override
    public String toString() {
        return super.toString() + ", model='" + model + "', warrantyPeriod=" + warrantyPeriod;
    }
}
