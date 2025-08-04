package com.niudada.order;

import lombok.Data;

@Data
public class Order {
    private String orderId;
    private String product;
    private int quantity;
    private double price;
    private OrderStatus status;

    public Order(String orderId, String product, int quantity, double price) {
        this.orderId = orderId;
        this.product = product;
        this.quantity = quantity;
        this.price = price;
        this.status = OrderStatus.PENDING;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", product='" + product + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", status=" + status.getValue() +
                '}';
    }
}
