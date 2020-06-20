package com.langdang.bigdata.services.impl;

import com.langdang.bigdata.dao.ProductDao;
import com.langdang.bigdata.domain.Product;
import com.langdang.bigdata.services.ProductServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductServices {

    @Autowired
    private ProductDao productDao;
    @Override
    public Product findByPid(Integer pid) {
        return productDao.findById(pid).get();
    }
}
