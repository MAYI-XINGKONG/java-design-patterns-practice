package com.niudada.order;

/**
 * 未支付状态
 */
public class UnpaidState implements OrderState {
    @Override
    public void payOrder(Order order) {
        System.out.println("订单已支付,状态从未支付变为已支付");
        order.setState(new PaidState());
    }

    @Override
    public void shipOrder(Order order) {
        System.out.println("订单未支付,无法发货");
    }

    @Override
    public void deliverOrder(Order order) {
        System.out.println("订单未支付,无法收货");
    }

    @Override
    public void cancelOrder(Order order) {
        System.out.println("订单已取消,状态从未支付改为已取消");
        order.setState(new CancelledState());
    }

    @Override
    public String getStateName() {
        return "未支付";
    }
}
