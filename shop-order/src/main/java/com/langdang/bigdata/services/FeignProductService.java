package com.langdang.bigdata.services;

import com.langdang.bigdata.domain.Product;
import com.langdang.bigdata.services.fallback.FeignProductServiceImpl;
import com.langdang.bigdata.services.fallback.ProductServiceFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
@Component
@FeignClient(value = "service-product",
//        fallback = FeignProductServiceImpl.class,
        fallbackFactory = ProductServiceFallbackFactory.class
)
public interface FeignProductService {

    /**
     * @FeignClient的value值+GetMapping的value值 其实就是完整的请求地址
     */
    @GetMapping("/product/{pid}")
     Product findByPid(@PathVariable("pid") Integer pid);
}
