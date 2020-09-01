package com.itmuch.gateway.RoutePredicateFactory.config;

import lombok.Data;

import java.time.LocalTime;

/**
 * @ClassName : TimeBetweenConfig
 * @Description :TODO <谓词工厂配置类>
 * @Author : Uniquek
 * @Date: 2020-08-28 16:33
 */
@Data
public class TimeBetweenConfig {
    private LocalTime start;
    private LocalTime end;
}
