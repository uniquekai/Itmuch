package com.itmuch.service.contentcenter.test;

import org.springframework.web.client.RestTemplate;

/**
 * @Auther: uniquek
 * @Date: 2020/2/11 12:55
 * @Description: 测试sentinel流控规则
 */
public class SentinelTest {
    public static void main(String[] args) throws InterruptedException {
        RestTemplate restTemplate= new RestTemplate();
        for (int i = 0;i < 1000; i++){
            String forObject = restTemplate.getForObject("http://localhost:8280/actuator/sentinel", String.class);
            Thread.sleep(500);
        }
    }
}
