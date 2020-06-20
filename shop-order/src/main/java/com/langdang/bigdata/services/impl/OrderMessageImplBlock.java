package com.langdang.bigdata.services.impl;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;

/**
 * OrderMessageImpl BlockException处理的类
 */
@Slf4j
public class OrderMessageImplBlock {

    /*
       定义blockHandler要求当前方法的返回值和原方法一直
        但是允许在参数列表的最后加入一个BlockException参数，用来接收原方法发生的异常
    */
    public static String blockHandler(String name, BlockException ex){
        //自定义异常处理逻辑
        log.error("触发了blockException,内容为{}",ex);
        return "BlockException";
    }


}
