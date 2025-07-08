package com.niudada.sku.base;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.math.BigDecimal;

@Slf4j
public class BaseTest {

    @Test
    public void testBase() {
        ClothingSku clothingSku = new ClothingSku("1", "T恤", new BigDecimal("19.99"), 100, "M", "白色");
        ElectronicSku electronicSku = new ElectronicSku("1", "笔记本电脑", new BigDecimal("8999.99"), 23, "MacBook Pro", 1);

        SkuFactoryImpl clothFactory = new SkuFactoryImpl(clothingSku);
        SkuFactoryImpl electronicFactory = new SkuFactoryImpl(electronicSku);

        // 如果这里不想使用强转，可以将工厂改为泛型方式
        ClothingSku tShirt1 = (ClothingSku) clothFactory.createProductSku();
        log.info("Clothing Sku 1: {}", tShirt1);
        ClothingSku tShirt2 = (ClothingSku) clothFactory.createProductSku();
        tShirt2.setSize("L");
        tShirt2.setColor("天空蓝");
        log.info("Clothing Sku 2: {}", tShirt2);

        ElectronicSku lapTop1 = (ElectronicSku) electronicFactory.createProductSku();
        log.info("Electronic Sku 1: {}", lapTop1);
        ElectronicSku lapTop2 = (ElectronicSku) electronicFactory.createProductSku();
        lapTop2.setModel("MacBook Air");
        lapTop2.setWarrantyPeriod(2);
        log.info("Electronic Sku 2: {}", lapTop2);
    }
}
