package com.niudada.order;

/**
 * 订单已收货/已完成状态
 */
public class DeliveredState implements OrderState {
    @Override
    public void payOrder(Order order) {
        System.out.println("订单已完成,无法支付");
    }

    @Override
    public void shipOrder(Order order) {
        System.out.println("订单已完成,无法发货");
    }

    @Override
    public void deliverOrder(Order order) {
        System.out.println("订单已完成,无法重复收货");
    }

    @Override
    public void cancelOrder(Order order) {
        System.out.println("订单已完成,无法取消");
    }

    @Override
    public String getStateName() {
        return "已完成(已收货)";
    }
}
