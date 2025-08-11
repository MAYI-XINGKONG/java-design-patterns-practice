package com.niudada.order;

/**
 * 订单已发货状态
 */
public class ShippedState implements OrderState {
    @Override
    public void payOrder(Order order) {
        System.out.println("订单已发货,无法支付");
    }

    @Override
    public void shipOrder(Order order) {
        System.out.println("订单已发货,无法重复发货");
    }

    @Override
    public void deliverOrder(Order order) {
        System.out.println("订单已发货,状态从已发货变为已完成");
        order.setState(new DeliveredState());
    }

    @Override
    public void cancelOrder(Order order) {
        System.out.println("订单已发货,无法取消");
    }

    @Override
    public String getStateName() {
        return "已发货";
    }
}
