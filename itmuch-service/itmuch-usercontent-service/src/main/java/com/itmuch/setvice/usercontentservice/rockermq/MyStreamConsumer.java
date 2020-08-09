package com.itmuch.setvice.usercontentservice.rockermq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Service;

/**
 * @ClassName : MyStreamConsumer
 * @Description :TODO <spring cloud alibaba stream 使用自定义消息接口的消息消费类>
 * @Author : Uniquek
 * @Date: 2020-08-09 17:37
 */
@Service
@Slf4j
public class MyStreamConsumer {
    @StreamListener(MySink.MY_INPUT)
    public void receive(String Message){
        log.info("自定义接口消费消息:{}",Message);
    }
}
