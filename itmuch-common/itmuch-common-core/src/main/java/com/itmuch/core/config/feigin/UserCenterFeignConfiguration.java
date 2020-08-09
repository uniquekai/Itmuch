package com.itmuch.core.config.feigin;

import feign.Logger;
import org.springframework.context.annotation.Bean;

/**
 * Created with IntelliJ IDEA.
 * User: uniquek
 * Date: 2019/12/26
 * Time: 8:04 下午
 * Description:使用Java代码自定义fegin的日志级别
 */
public class UserCenterFeignConfiguration {
    @Bean
    public Logger.Level level() {
        //让fegin打印所有请求的细节
        return Logger.Level.FULL;
    }
}