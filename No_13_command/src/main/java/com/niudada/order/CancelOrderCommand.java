package com.niudada.order;

import java.util.List;

public class CancelOrderCommand implements OrderCommand {
    private Order order;
    private List<Order> orderList;
    private OrderStatus previousStatus;

    public CancelOrderCommand(Order order, List<Order> orderList) {
        this.order = order;
        this.orderList = orderList;
    }

    @Override
    public void execute() {
        previousStatus = order.getStatus();
        order.setStatus(OrderStatus.CANCELED);
        System.out.println("取消订单：" + order);
    }

    @Override
    public void undo() {
        order.setStatus(previousStatus);
        System.out.println("撤销取消订单：" + order);
    }
}
