package com.niudada.sku.shallow_copy;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.math.BigDecimal;

@Slf4j
public class ShallowCopyTest {

    @Test
    public void testShallowCopy() {
        Address defaultAddress = new Address("广州", "朱雀大街");

        ClothingSku clothingSku = new ClothingSku("1", "T恤", new BigDecimal("19.99"), 100, "L", "黑色", defaultAddress);
        SkuFactoryImpl clothingFactory = new SkuFactoryImpl(clothingSku);
        ClothingSku tShirt1 = (ClothingSku) clothingFactory.createProductSku();
        log.info("tShirt Sku 1: {}", tShirt1);
        // -- 克隆并定制化
        ClothingSku tShirt2 = (ClothingSku) clothingFactory.createProductSku();
        tShirt2.setSize("XL");
        tShirt2.setColor("白色");
        /** 浅拷贝测试 -- start **/
        tShirt2.getAddress().setCity("深圳");
        tShirt2.getAddress().setStreet("深圳大道");
        /**
         * 这里tShirt1的address信息也被修改了；
         * 是因为浅拷贝下，tShirt1和tShirt2共享同一个address对象，只复制了tShirt对象本身及address引用(没有复制引用的对象)
         */
        log.info("tShirt Sku 1: {}", tShirt1);
        /** 浅拷贝测试 -- end **/
        log.info("tShirt Sku 2: {}", tShirt2);

        ElectronicSku electronicSku = new ElectronicSku("1", "笔记本电脑", new BigDecimal("8999.99"), 23, "MacBook Pro", 1);
        SkuFactoryImpl electronicFactory = new SkuFactoryImpl(electronicSku);
        ElectronicSku lapTop1 = (ElectronicSku) electronicFactory.createProductSku();
        log.info("Electronic Sku 1: {}", lapTop1);
        // --
        ElectronicSku lapTop2 = (ElectronicSku) electronicFactory.createProductSku();
        lapTop2.setModel("MacBook Air");
        lapTop2.setWarrantyPeriod(2);
        log.info("Electronic Sku 2: {}", lapTop2);
    }

}
