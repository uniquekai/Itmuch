package com.itmuch.setvice.usercontentservice;


import cn.hutool.core.util.NetUtil;
import com.itmuch.setvice.usercontentservice.rockermq.MySink;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Sink;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Slf4j
@EnableDiscoveryClient
@MapperScan("com.itmuch.common.mybatisplus")
@SpringBootApplication(scanBasePackages = "com.itmuch")
@EnableFeignClients("com.itmuch")
@EnableSwagger2
@EnableBinding({Sink.class, MySink.class})
public class ItmuchUsercontentServiceApplication {
    public static void main(String[] args) {
        int port = 8180;
        int maxPort = port + 20;
        while (!NetUtil.isUsableLocalPort(port)) {
            if (port > maxPort) {
                log.error("端口超出范围");
            }
            port++;
            log.error("端口占用->>{}", port);
        }
        new SpringApplicationBuilder(ItmuchUsercontentServiceApplication.class).
                properties("server.port=" + port).run(args);
    }

}