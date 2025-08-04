package com.niudada.order;

public enum OrderStatus {
    PENDING("挂起"),
    CONFIRMED("确认"),
    CANCELED("取消"),
    DELETED("删除");

    private final String value;

    OrderStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
