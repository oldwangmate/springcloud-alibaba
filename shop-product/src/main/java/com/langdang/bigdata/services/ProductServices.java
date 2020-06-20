package com.langdang.bigdata.services;

import com.langdang.bigdata.domain.Product;

public interface ProductServices {
    //根据PId查询商品信息
    Product findByPid(Integer pid);
}
