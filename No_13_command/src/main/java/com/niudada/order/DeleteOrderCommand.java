package com.niudada.order;

import java.util.List;

public class DeleteOrderCommand implements OrderCommand {
    private Order order;
    private List<Order> orderList;
    private OrderStatus previousStatus;
    private Boolean wasRemoved;

    public DeleteOrderCommand(Order order, List<Order> orderList) {
        this.order = order;
        this.orderList = orderList;
    }
    @Override
    public void execute() {
        previousStatus = order.getStatus();
        wasRemoved = orderList.remove(order);
        if (wasRemoved) {
            order.setStatus(OrderStatus.DELETED);
            System.out.println("删除订单：" + order);
        }
    }

    @Override
    public void undo() {
        if (wasRemoved) {
            order.setStatus(previousStatus);
            orderList.add(order);
            System.out.println("撤销删除订单：" + order);
        }
    }
}
