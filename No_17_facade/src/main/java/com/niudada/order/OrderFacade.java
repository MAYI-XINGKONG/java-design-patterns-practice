package com.niudada.order;

import java.util.Arrays;
import java.util.List;

public class OrderFacade {
    private final List<OrderProcessor> processors;

    public OrderFacade() {
        processors = Arrays.asList(
                new InventoryProcessor(),
                new PaymentProcessor(),
                new ShippingProcessor()
        );
    }

    public void processOrder(String orderId) {
        System.out.println("开始处理订单：" + orderId);
        processors.forEach(processor -> processor.process(orderId));
        System.out.println("订单处理完成：" + orderId);
    }
}
