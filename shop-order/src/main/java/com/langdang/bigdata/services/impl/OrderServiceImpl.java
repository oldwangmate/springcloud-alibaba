package com.langdang.bigdata.services.impl;


import com.langdang.bigdata.dao.OrderrDao;
import com.langdang.bigdata.domain.Order;
import com.langdang.bigdata.services.OrderServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderServices {
    @Autowired
    private OrderrDao orderrDao;
    @Override
    public void createOrder(Order order) {
        orderrDao.save(order);
    }
}
