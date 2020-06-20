package com.langdang.bigdata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient  //开启nacos客户端
public class ProducBackuptApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProducBackuptApplication.class);
    }
}
