package com.langdang.bigdata.controller;

import com.alibaba.fastjson.JSON;
import com.langdang.bigdata.domain.Product;
import com.langdang.bigdata.services.ProductServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class ProductController {

    @Autowired
    private ProductServices productServices;
    //商品信息查询
    @RequestMapping("/product/{pid}")
    public Product product(@PathVariable("pid") Integer pid){
        log.info("接下来要进行{}号商品查询",pid);
        Product product = productServices.findByPid(pid);
        log.info("商品查询成功，内容为{}",JSON.toJSONString(product));
        return product;
    }
}
