package com.niudada.order;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class OrderTest {

    @Test
    public void test() {
        log.info("1、订单处理系统演示:");
        List<Order> orders = new ArrayList<>();
        OrderInvoker orderInvoker = new OrderInvoker();

        // 创建订单
        Order order = new Order("1", "MacBook Pro", 2, 25998.00);
        OrderCommand createCommand = new CreateOrderCommand(order, orders);
        orderInvoker.executeCommand(createCommand);

        // 撤销上一个操作(创建订单)
        orderInvoker.undoLastCommand();

        System.out.println("订单列表大小:" + orders.size());

        // 创建订单
        Order order1 = new Order("2", "笔记本电脑", 1, 5999.00);
        OrderCommand createOrderCommand = new CreateOrderCommand(order1, orders);
        orderInvoker.executeCommand(createOrderCommand);

        // 取消订单
        OrderCommand cancelOrderCommand = new CancelOrderCommand(order1, orders);
        orderInvoker.executeCommand(cancelOrderCommand);

        System.out.println("订单列表大小:" + orders.size());

        // 撤销上一个操作(取消订单)
        orderInvoker.undoLastCommand();
        System.out.println("订单列表大小:" + orders.size());

        // 删除订单
        OrderCommand deleteOrderCommand = new DeleteOrderCommand(order1, orders);
        orderInvoker.executeCommand(deleteOrderCommand);

        System.out.println("订单列表大小:" + orders.size());

        // 撤销上一个操作(删除订单)
        orderInvoker.undoLastCommand();

        System.out.println("最终订单状态:" + order1);
        System.out.println("订单列表大小:" + orders.size());
    }
}