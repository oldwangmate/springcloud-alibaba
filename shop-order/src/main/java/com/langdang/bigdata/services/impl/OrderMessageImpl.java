package com.langdang.bigdata.services.impl;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.langdang.bigdata.services.OrderMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OrderMessageImpl implements OrderMessage {
    private int i = 0;

    //定义资源 value指定资源名称
    @SentinelResource("message")
    public String message() {
        return "message";
    }

    /*
     定义一个资源也可以定义定义当资源内部发生异常的时候的处理逻辑
     blockHandler 定义当个资源内部发生了BlockException应该进入的方法【捕获的是sentinel定义的异常】
     fallback 定义当资源发生了Throwable应该进入的方法
   */
    @Override
    @SentinelResource(value = "message6",
            blockHandlerClass = OrderMessageImplBlock.class,
            blockHandler = "blockHandler",
            fallbackClass = OrderMessageImplFallBack.class,
            fallback = "fallback" )
    public String message6(String name) {
        i ++ ;
        if(i % 3 == 0){
            throw new RuntimeException();
        }
        return name;
    }

    /*
        定义blockHandler要求当前方法的返回值和原方法一直
        但是允许在参数列表的最后加入一个BlockException参数，用来接收原方法发生的异常
     */
//    public String blockHandler(String name, BlockException ex){
//        //自定义异常处理逻辑
//        log.error("触发了blockException,内容为{}",ex);
//        return "BlockException";
//    }

    /*
        定义blockHandler要求当前方法的返回值和原方法一直
        但是允许在参数列表的最后加入一个Throwable参数，用来接收原方法发生的异常
     */
//    public String fallback(String name,  Throwable ex){
//        //自定义异常处理逻辑
//        log.error("触发了Throwable,内容为{}",ex);
//        return "Throwable";
//    }
}
