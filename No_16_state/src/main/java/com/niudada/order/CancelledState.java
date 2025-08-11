package com.niudada.order;

/**
 * 订单已取消状态
 */
public class CancelledState implements OrderState {
    @Override
    public void payOrder(Order order) {
        System.out.println("订单已取消，无法支付");
    }

    @Override
    public void shipOrder(Order order) {
        System.out.println("订单已取消，无法发货");
    }

    @Override
    public void deliverOrder(Order order) {
        System.out.println("订单已取消，无法收货");
    }

    @Override
    public void cancelOrder(Order order) {
        System.out.println("订单已取消，无法重复取消");
    }

    @Override
    public String getStateName() {
        return "已取消";
    }
}
