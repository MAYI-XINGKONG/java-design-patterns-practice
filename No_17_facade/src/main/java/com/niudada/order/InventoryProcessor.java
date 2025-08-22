package com.niudada.order;

public class InventoryProcessor extends OrderProcessor {
    @Override
    public void process(String orderId) {
        System.out.println("检查订单 " + orderId + " 的库存...");
        System.out.println("库存充足,已锁定商品");
    }
}
