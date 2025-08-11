package com.niudada.order;

/**
 * 已支付状态
 */
public class PaidState implements OrderState {
    @Override
    public void payOrder(Order order) {
        System.out.println("订单已支付,无需重复支付");
    }

    @Override
    public void shipOrder(Order order) {
        System.out.println("订单已发货,状态已支付变为已发货");
        order.setState(new ShippedState());
    }

    @Override
    public void deliverOrder(Order order) {
        System.out.println("订单尚未发货,无法收货");
    }

    @Override
    public void cancelOrder(Order order) {
        System.out.println("订单已取消,状态已支付变为已取消");
        // 一般这种情况还会调用退款API
        System.out.println("已调用退款服务接口完成退款");
        order.setState(new CancelledState());
    }

    @Override
    public String getStateName() {
        return "已支付";
    }
}
