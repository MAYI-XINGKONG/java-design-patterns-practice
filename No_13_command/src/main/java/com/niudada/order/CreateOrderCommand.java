package com.niudada.order;

import java.util.List;

public class CreateOrderCommand implements OrderCommand {
    private Order order;
    private List<Order> orderList;

    public CreateOrderCommand(Order order, List<Order> orderList) {
        this.order = order;
        this.orderList = orderList;
    }

    @Override
    public void execute() {
        orderList.add(order);
        order.setStatus(OrderStatus.CONFIRMED);
        System.out.println("创建订单：" + order);
    }

    @Override
    public void undo() {
        order.setStatus(OrderStatus.PENDING);
        System.out.println("撤销创建订单：" + order);
    }
}
