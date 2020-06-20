package com.langdang.bigdata.services.fallback;

import com.langdang.bigdata.domain.Product;
import com.langdang.bigdata.services.FeignProductService;
import org.springframework.stereotype.Component;

/**
 * 这是一个容错类 需要实现feign并实现接口中的所有方法，一旦feign远程调用出现问题就会进入当前类同名的方法中执行容错逻辑
 */
@Component
public class FeignProductServiceImpl implements FeignProductService {
    @Override
    public Product findByPid(Integer pid) {
        //容错逻辑
        Product product = new Product();
        product.setPid(-1);
        product.setPname("远程调用商品微服务异常，进入了容错逻辑");
        return product;
    }
}
