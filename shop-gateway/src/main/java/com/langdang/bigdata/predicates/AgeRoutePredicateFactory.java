package com.langdang.bigdata.predicates;

import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * 自定义路由断言工厂类
 * 要求有两个
 * 1.名字必须是配置+RoutePredicateFactory
 * 2.必须继承AbstractRoutePredicateFactory<配置类>
 */
@Component
public class AgeRoutePredicateFactory extends AbstractRoutePredicateFactory<AgeRoutePredicateFactory.Config> {

    //构造函数
    public AgeRoutePredicateFactory() {
        super(AgeRoutePredicateFactory.Config.class);
    }

    //读取配置文件中的参数值，赋值到配置类中属性上
    public List<String> shortcutFieldOrder() {
        //这个位置的顺序必须和配置文件中的值顺序对应
        return Arrays.asList("minAge","maxAge");
    }

    /**
     * 断言逻辑
     */
    @Override
    public Predicate<ServerWebExchange> apply(Config config) {
        return new Predicate<ServerWebExchange>() {
            @Override
            public boolean test(ServerWebExchange serverWebExchange) {
                //1.接受前台传入的Age参数
                String ageStr = serverWebExchange.getRequest().getQueryParams().getFirst("age");
                //2.判断是否为空
                if(StringUtils.isNotEmpty(ageStr)){
                    int age = Integer.parseInt(ageStr);
                    if(age < config.MaxAge && age > config.getMinAge()){
                        return true;
                    }else {
                        return false;
                    }
                }
              return false;
            }
        };
    }


    /**
     * 配置类用于接受配置文件中的参数
     */
    public static class Config{
        private int minAge;
        private int MaxAge;

        public Config() {
        }

        public Config(int minAge, int maxAge) {
            this.minAge = minAge;
            MaxAge = maxAge;
        }

        public int getMinAge() {
            return minAge;
        }

        public void setMinAge(int minAge) {
            this.minAge = minAge;
        }

        public int getMaxAge() {
            return MaxAge;
        }

        public void setMaxAge(int maxAge) {
            MaxAge = maxAge;
        }
    }
}
