package com.niudada.sku.shallow_copy;

public class SkuFactoryImpl implements SkuFactory{
    private final ProductSku prototype;

    public SkuFactoryImpl(ProductSku prototype) {
        this.prototype = prototype;
    }
    @Override
    public ProductSku createProductSku() {
        return prototype.copy();
    }
}
