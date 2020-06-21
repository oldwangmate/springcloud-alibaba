package com.langdang.bigdata.filters;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

/**
 * 自定义局部路由过滤器
 */
@Component
public class LogGatewayFilterFactory extends AbstractGatewayFilterFactory<LogGatewayFilterFactory.Config> {

    //构造方法
    public LogGatewayFilterFactory(){
        super(LogGatewayFilterFactory.Config.class);
    }

    //读取配置文件中的参数 赋值到配置类中


    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList("consoleLog","cacheLog");
    }

    //过滤器规则
    @Override
    public GatewayFilter apply(Config config) {
        return new GatewayFilter() {
            @Override
            public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
                if(config.isCacheLog()){
                    System.out.println("缓存日志以开启");
                }
                if(config.isConsoleLog()){
                    System.out.println("控制台日志以开启");
                }
                return chain.filter(exchange);
            }
        };
    }

    // 配置类 接收配置参数
    public static class Config {
        private boolean consoleLog;
        private boolean cacheLog;

        public Config() {
        }

        public boolean isConsoleLog() {
            return consoleLog;
        }

        public void setConsoleLog(boolean consoleLog) {
            this.consoleLog = consoleLog;
        }

        public boolean isCacheLog() {
            return cacheLog;
        }

        public void setCacheLog(boolean cacheLog) {
            this.cacheLog = cacheLog;
        }
    }
}
