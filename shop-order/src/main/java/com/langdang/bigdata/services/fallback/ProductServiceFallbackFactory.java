package com.langdang.bigdata.services.fallback;

import com.langdang.bigdata.domain.Product;
import com.langdang.bigdata.services.FeignProductService;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 这是容错类，要求我们实现FallbackFactory<要为那个接口产生容错类>
 */
@Component
@Slf4j
public class ProductServiceFallbackFactory implements FallbackFactory<FeignProductService> {
    //throwable就是feign在调用过程中产生的异常
    @Override
    public FeignProductService create(Throwable throwable) {
        return new FeignProductService(){
            @Override
            public Product findByPid(Integer pid) {
                log.error("{}",throwable);
                throwable.printStackTrace();
                Product product = new Product();
                product.setPid(-1);
                return product;
            }
        };
    }
}
