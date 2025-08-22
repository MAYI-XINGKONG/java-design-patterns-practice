package com.niudada.order;

public class ShippingProcessor extends OrderProcessor {
    @Override
    public void process(String orderId) {
        System.out.println("安排订单 " + orderId + " 的发货...");
        System.out.println("已生成运单,商品已出库");
    }
}
