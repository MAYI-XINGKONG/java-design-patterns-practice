package com.niudada.order;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class OrderTest {

    @Test
    public void test() {
        log.info("【ORD001订单状态测试开始...】");
        log.info("--- 创建订单 ---");
        Order order = new Order("ORD001");
        order.showState();
        log.info("--- 发货(此时还未支付) ---");
        order.ship();
        order.showState();
        log.info("--- 支付订单 ---");
        order.pay();
        order.showState();
        log.info("--- 发货(此时已支付) ---");
        order.ship();
        order.showState();
        log.info("--- 收货 ---");
        order.deliver();
        order.showState();
        log.info("--- 再次支付(此时订单已收货) ---");
        order.pay();


        log.info("【ORD002订单状态测试开始...】");
        log.info("--- 创建订单 ---");
        Order order2 = new Order("ORD002");
        order2.showState();
        log.info("--- 支付订单 ---");
        order2.pay();
        order2.showState();
        log.info("--- 取消订单 ---");
        order2.cancel();
        order2.showState();
        log.info("--- 尝试发货(此时已取消) ---");
        order2.ship();

    }
}
