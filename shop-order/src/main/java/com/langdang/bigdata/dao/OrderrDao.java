package com.langdang.bigdata.dao;

import com.langdang.bigdata.domain.Order;
import com.langdang.bigdata.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderrDao extends JpaRepository<Order,Integer> {
}
