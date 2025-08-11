package com.niudada.order;

public interface OrderState {
    void payOrder(Order order);

    void shipOrder(Order order);

    void deliverOrder(Order order);

    void cancelOrder(Order order);

    String getStateName();
}
