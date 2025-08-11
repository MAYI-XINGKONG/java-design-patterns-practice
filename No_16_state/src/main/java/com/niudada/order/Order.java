package com.niudada.order;

import lombok.Getter;
import lombok.Setter;

public class Order {

    @Getter
    @Setter
    private OrderState state;

    @Getter
    private String orderId;

    public Order(String orderId) {
        this.orderId = orderId;
        // 默认订单状态为未支付
        this.state = new UnpaidState();
        System.out.println("创建订单 " + orderId + "，初始状态为：" + state.getStateName());
    }

    /**
     * 委托给状态对象去处理具体行为
     */
    public void pay() {
        System.out.println("执行支付操作...");
        state.payOrder(this);
    }

    public void ship() {
        System.out.println("执行发货操作...");
        state.shipOrder(this);
    }

    public void deliver() {
        System.out.println("执行收货操作...");
        state.deliverOrder(this);
    }

    public void cancel() {
        System.out.println("执行取消操作...");
        state.cancelOrder(this);
    }

    public void showState() {
        System.out.println("订单 " + orderId + " 当前状态：" + state.getStateName());
    }
}
