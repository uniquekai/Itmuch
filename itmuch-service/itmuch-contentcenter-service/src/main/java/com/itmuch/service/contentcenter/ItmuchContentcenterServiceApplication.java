package com.itmuch.service.contentcenter;

import cn.hutool.core.util.NetUtil;
import com.itmuch.core.config.sentinel.ExceptionUtil;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;

import org.springframework.cloud.alibaba.sentinel.annotation.SentinelRestTemplate;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@Slf4j
@EnableDiscoveryClient
@MapperScan("com.itmuch.common.mybatisplus")
@SpringBootApplication(scanBasePackages = "com.itmuch")
@EnableFeignClients("com.itmuch")
public class ItmuchContentcenterServiceApplication {

    public static void main(String[] args) {
//        SpringApplication.run(ItmuchContentcenterServiceApplication.class, args);
        int port = 8280;
        int maxPort = port + 20;

        while (!NetUtil.isUsableLocalPort(port)) {
            if (port > maxPort) {
                log.error("端口超出范围");
            }
            port++;
            log.error("端口占用->>{}", port);
        }

        new SpringApplicationBuilder(ItmuchContentcenterServiceApplication.class).
                properties("server.port=" + port).run(args);

    }

    @Bean
    @SentinelRestTemplate(fallback = "fallback", fallbackClass = ExceptionUtil.class, blockHandler="handleException",blockHandlerClass=ExceptionUtil.class)
    @LoadBalanced//为RestTemplate整合Ribbon
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
