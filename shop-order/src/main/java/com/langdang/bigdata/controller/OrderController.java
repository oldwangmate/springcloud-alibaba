package com.langdang.bigdata.controller;

import com.alibaba.fastjson.JSON;
import com.langdang.bigdata.domain.Order;
import com.langdang.bigdata.domain.Product;
import com.langdang.bigdata.services.FeignProductService;
import com.langdang.bigdata.services.OrderServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Random;

@RestController
@Slf4j
public class OrderController {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private OrderServices orderServices;
    @Autowired
    private DiscoveryClient discoveryClient;
    @Autowired
    private FeignProductService feignProductService;

    //下单 使用Fegin
    @RequestMapping("/order/prod/{pid}")
    public Order order(@PathVariable("pid") Integer pid){
        log.info("接收到{}号商品的下单请求，调用商品微服务查询商品信息",pid);
        Product product = feignProductService.findByPid(pid);
        if(product.getPid() == -1){
            Order order = new Order();
            order.setOid(-1L);
            order.setPname("下单失败");
            return order;
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
        orderServices.createOrder(order);
        log.info("创建订单成功,订单信息为{}",JSON.toJSONString(order));
        return order;
    }




//    //下单 使用ribbon负载均衡
//    @RequestMapping("/order/prod/{pid}")
//    public Order order(@PathVariable("pid") Integer pid){
//        log.info("接收到{}号商品的下单请求，调用商品微服务查询商品信息",pid);
//        Product product = restTemplate.getForObject("http://service-product/product/"+pid,Product.class);
//        log.info("查询到{}号商品的信息，内容是{}",pid,JSON.toJSONString(product));
//        //下单（创建订单）
//        Order order = new Order();
//        order.setUid(1);
//        order.setUsername("测试用户");
//        order.setPid(pid);
//        order.setPname(product.getPname());
//        order.setPprice(product.getPprice());
//        order.setNumber(1);
//        orderServices.createOrder(order);
//        log.info("创建订单成功,订单信息为{}",JSON.toJSONString(order));
//        return order;
//    }


//    //下单 自定义负载均衡
//    @RequestMapping("/order/prod/{pid}")
//    public Order order(@PathVariable("pid") Integer pid){
//        log.info("接收到{}号商品的下单请求，调用商品微服务查询商品信息",pid);
//
//        //调用商品微服务查询商品信息
//        /**
//         * 问题
//         * 1.一旦服务提供者的地址信息发生了变化，我们就不得不去修改服务调用者的java代码、
//         * 2.一旦服务提供者做了集群，服务调用者无法实现负载均衡
//         * 3.一旦微服务变得越来越多，如何管理这个服务清单就成了问题
//         */
//        List<ServiceInstance> instances = discoveryClient.getInstances("service-product");
//        //随机选择
//        int index = new Random().nextInt(instances.size());
//        ServiceInstance serviceInstance = instances.get(index);
//        String host = serviceInstance.getHost();
//        int port = serviceInstance.getPort();
//        Product product = restTemplate.getForObject("http://"+host+":"+port+"/product/"+pid,Product.class);
//        log.info("查询到{}号商品的信息，内容是{}",pid,JSON.toJSONString(product));
//        //下单（创建订单）
//        Order order = new Order();
//        order.setUid(1);
//        order.setUsername("测试用户");
//        order.setPid(pid);
//        order.setPname(product.getPname());
//        order.setPprice(product.getPprice());
//        order.setNumber(1);
//        orderServices.createOrder(order);
//        log.info("创建订单成功,订单信息为{}",JSON.toJSONString(order));
//        return order;
//    }



    //下单
//    @RequestMapping("/order/prod/{pid}")
//    public Order order(@PathVariable("pid") Integer pid){
//        log.info("接收到{}号商品的下单请求，调用商品微服务查询商品信息",pid);
//
//        //调用商品微服务查询商品信息
//        /**
//         * 问题
//         * 1.一旦服务提供者的地址信息发生了变化，我们就不得不去修改服务调用者的java代码、
//         * 2.一旦服务提供者做了集群，服务调用者无法实现负载均衡
//         * 3.一旦微服务变得越来越多，如何管理这个服务清单就成了问题
//         */
//        List<ServiceInstance> instances = discoveryClient.getInstances("service-product");
//        ServiceInstance serviceInstance = instances.get(0);
//        String host = serviceInstance.getHost();
//        int port = serviceInstance.getPort();
//        Product product = restTemplate.getForObject("http://"+host+":"+port+"/product/"+pid,Product.class);
//        log.info("查询到{}号商品的信息，内容是{}",pid,JSON.toJSONString(product));
//        //下单（创建订单）
//        Order order = new Order();
//        order.setUid(1);
//        order.setUsername("测试用户");
//        order.setPid(pid);
//        order.setPname(product.getPname());
//        order.setPprice(product.getPprice());
//        order.setNumber(1);
//        orderServices.createOrder(order);
//        log.info("创建订单成功,订单信息为{}",JSON.toJSONString(order));
//        return order;
//    }



//    //下单
//    @RequestMapping("/order/prod/{pid}")
//    public Order order(@PathVariable("pid") Integer pid){
//        log.info("接收到{}号商品的下单请求，调用商品微服务查询商品信息",pid);
//
//        //调用商品微服务查询商品信息
//        /**
//         * 问题
//         * 1.一旦服务提供者的地址信息发生了变化，我们就不得不去修改服务调用者的java代码、
//         * 2.一旦服务提供者做了集群，服务调用者无法实现负载均衡
//         * 3.一旦微服务变得越来越多，如何管理这个服务清单就成了问题
//         */
//        Product product = restTemplate.getForObject("http://localhost:8081/product/"+pid,Product.class);
//        log.info("查询到{}号商品的信息，内容是{}",pid,JSON.toJSONString(product));
//        //下单（创建订单）
//        Order order = new Order();
//        order.setUid(1);
//        order.setUsername("测试用户");
//        order.setPid(pid);
//        order.setPname(product.getPname());
//        order.setPprice(product.getPprice());
//        order.setNumber(1);
//        orderServices.createOrder(order);
//        log.info("创建订单成功,订单信息为{}",JSON.toJSONString(order));
//        return order;
//    }
}
