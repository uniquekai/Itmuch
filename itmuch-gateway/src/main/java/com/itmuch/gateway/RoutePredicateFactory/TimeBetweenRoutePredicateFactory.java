package com.itmuch.gateway.RoutePredicateFactory;

import com.itmuch.gateway.RoutePredicateFactory.config.TimeBetweenConfig;
import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * @ClassName : TimeBetweenRoutePredicateFactory
 * @Description :TODO <路由谓词工厂：访问时间在两者之间>
 *     1. 路由谓词工厂要以RoutePredicateFactory结尾，这是springcloud gateway的一个约定
 * @Author : Uniquek
 * @Date: 2020-08-28 16:18
 */
@Component
public class TimeBetweenRoutePredicateFactory extends AbstractRoutePredicateFactory<TimeBetweenConfig> {

    public TimeBetweenRoutePredicateFactory() {
        super(TimeBetweenConfig.class);
    }

    @Override
    public Predicate<ServerWebExchange> apply(TimeBetweenConfig config) {
        LocalTime start = config.getStart();
        LocalTime end = config.getEnd();
        return exchange -> {
            LocalTime now = LocalTime.now();
            return now.isAfter(start) && now.isBefore(end);
        };
    }

    /**
     * 功能描述: TODO:<这个类是用于映射自定义谓词配置类与配置文件中自定义谓词的的关系>
     * @Param: []
     * @Return: java.util.List<java.lang.String>
     * @Author: Uniquek
     * @Date: 2020/8/28 4:37 下午
     */
    @Override
    public List<String> shortcutFieldOrder() {
        /**
         * 这样写映射为:TimeBetweenConfig的start为第一参数，end为第二个参数
         */
        return Arrays.asList("start","end");
    }

    public static void main(String[] args) {
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT);
        System.out.println(formatter.format(LocalTime.now()));
    }
}
