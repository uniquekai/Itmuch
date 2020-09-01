package com.itmuch.gateway;

import cn.hutool.core.util.NetUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
@Slf4j
public class ItmuchGatewayApplication {

    public static void main(String[] args) {
        int port = 8380;
        int maxPort = port + 20;

        while (!NetUtil.isUsableLocalPort(port)) {
            if (port > maxPort) {
                log.error("端口超出范围");
            }
            port++;
            log.error("端口占用->>{}", port);
        }

        new SpringApplicationBuilder(ItmuchGatewayApplication.class).
                properties("server.port=" + port).run(args);
    }

}
