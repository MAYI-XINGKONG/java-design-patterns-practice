package com.niudada.sku.deep_copy;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.math.BigDecimal;

@Slf4j
public class DeepCopyTest {

    @Test
    public void testDeepCopy() {
        Address defaultAddress = new Address("广州", "朱雀大街");
        ClothingSku clothingSku = new ClothingSku("1", "T桖", new BigDecimal("19.99"), 100, "M", "白色", defaultAddress);
        SkuFactoryImpl clothingFactory = new SkuFactoryImpl(clothingSku);
        ClothingSku tShirt1 = (ClothingSku) clothingFactory.createProductSku();
        log.info("Clothing Sku 1: {}", tShirt1);
        // --克隆
        ClothingSku tShirt2 = (ClothingSku) clothingFactory.createProductSku();
        tShirt2.setSize("XL");
        tShirt2.setColor("黑色");
        /** 深拷贝测试 -- start **/
        tShirt2.getAddress().setCity("深圳");
        tShirt2.getAddress().setStreet("深圳大道");
        /**
         * 这里tShirt1的address信息没有发生变化；
         * 是因为深拷贝下，tShirt2复制整个对象图，即复制了tShirt对象本身并递归复制了所有引用对象；
         * 所以tShirt2的address对象和tShirt1的address对象是完全独立的两个对象
         */
        log.info("Clothing Sku 1: {}", tShirt1);
        /** 深拷贝测试 -- end **/
        log.info("Clothing Sku 2: {}", tShirt2);

        //ElectronicSku一样的 就不写了...
    }
}
