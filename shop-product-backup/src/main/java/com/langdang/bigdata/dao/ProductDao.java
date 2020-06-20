package com.langdang.bigdata.dao;

import com.langdang.bigdata.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductDao extends JpaRepository<Product,Integer> {
}
