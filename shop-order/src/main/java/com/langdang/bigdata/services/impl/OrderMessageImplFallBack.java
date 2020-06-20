package com.langdang.bigdata.services.impl;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OrderMessageImplFallBack {

    /*
    定义blockHandler要求当前方法的返回值和原方法一直
    但是允许在参数列表的最后加入一个Throwable参数，用来接收原方法发生的异常
 */
    public static String fallback(String name,  Throwable ex){
        //自定义异常处理逻辑
        log.error("触发了Throwable,内容为{}",ex);
        return "Throwable";
    }
}
