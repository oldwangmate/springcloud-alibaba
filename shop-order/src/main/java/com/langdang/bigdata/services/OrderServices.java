package com.langdang.bigdata.services;

import com.langdang.bigdata.domain.Order;

public interface OrderServices {
    //创建订单
    void createOrder(Order order);
}
