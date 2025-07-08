package com.niudada.sku.base;

public class SkuFactoryImpl implements SkuFactory{
    private final ProductSku productSku;

    public SkuFactoryImpl(ProductSku productSku) {
        this.productSku = productSku;
    }

    @Override
    public ProductSku createProductSku() {
        return productSku.copy();
    }
}
