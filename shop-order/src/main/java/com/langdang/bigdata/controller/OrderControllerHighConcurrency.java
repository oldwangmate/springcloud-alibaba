package com.langdang.bigdata.controller;

import com.alibaba.fastjson.JSON;
import com.langdang.bigdata.domain.Order;
import com.langdang.bigdata.domain.Product;
import com.langdang.bigdata.services.FeignProductService;
import com.langdang.bigdata.services.OrderServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@Slf4j
public class OrderControllerHighConcurrency {

    @Autowired
    private OrderServices orderServices;
    @Autowired
    private FeignProductService feignProductService;

    //下单 使用Fegin
    @RequestMapping("/order2/prod2/{pid}")
    public Order order(@PathVariable("pid") Integer pid){
        log.info("接收到{}号商品的下单请求，调用商品微服务查询商品信息",pid);
        Product product = feignProductService.findByPid(pid);
        //模拟商品调用微服务的时间
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("查询到{}号商品的信息，内容是{}",pid,JSON.toJSONString(product));
        //下单（创建订单）
        Order order = new Order();
        order.setUid(1);
        order.setUsername("测试用户");
        order.setPid(pid);
        order.setPname(product.getPname());
        order.setPprice(product.getPprice());
        order.setNumber(1);
        //为了不产生大量垃圾数据
//        orderServices.createOrder(order);
        log.info("创建订单成功,订单信息为{}",JSON.toJSONString(order));
        return order;
    }

    //测试高并发
    @RequestMapping("/order2/message")
    public String message(){
        return "测试高并发";
    }

}
