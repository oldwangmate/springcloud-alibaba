package com.langdang.bigdata.filters;

import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 自定义全局过滤器需要实现GlobalFilter和Ordered接口
 * 作用是统一鉴权
 * 要求必须实现两个接口 并且实现其中两个方法
 */
@Component
public class AuthGlobalFilter implements GlobalFilter,Ordered {
    //过滤器逻辑
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //统一鉴权逻辑
        String token = exchange.getRequest().getQueryParams().getFirst("token");
        if(StringUtils.isBlank(token)){
            System.out.println("鉴权失败");
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }
        return chain.filter(exchange);
    }

    //表示当前过滤器的优先级，返回值越小，优先级越高
    @Override
    public int getOrder() {
        return 0;
    }
}
