package com.langdang.bigdata.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.fastjson.JSON;
import com.langdang.bigdata.domain.Order;
import com.langdang.bigdata.domain.Product;
import com.langdang.bigdata.services.FeignProductService;
import com.langdang.bigdata.services.OrderMessage;
import com.langdang.bigdata.services.OrderServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.criteria.CriteriaBuilder;

@RestController
@Slf4j
public class OrderControllerSentinel {

    private int i = 0;

    @Autowired
    private OrderMessage orderMessage;

    @RequestMapping("/order3/message3")
    public String message3(){
        i ++ ;
        if(i % 3 == 0){
            throw new RuntimeException();
        }
        orderMessage.message();
        return "message3";
    }

    @RequestMapping("/order4/message4")
    public String messag4(){
        orderMessage.message();
        return "messages4";
    }

    @RequestMapping("/order5/message5")
    @SentinelResource(value = "message5" )
    public String messag5(String name, Integer age){
        return "message5:"+name + age;
    }

    @RequestMapping("/order6/message6")
    public String messag6(String name){
       return orderMessage.message6(name);
    }

}
