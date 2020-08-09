package com.itmuch.setvice.usercontentservice.rockermq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.stereotype.Service;

/**
 * @ClassName : TestStreamCOnsumer
 * @Description : TODO<使用springcloud stream 自带的消息消费接口接收消息>
 * @Author : Uniquek
 * @Date: 2020-08-09 16:58
 */
@Service
@Slf4j
public class TestStreamConsumer {
    /**
     * 功能描述: TODO:<消息接收方法>
     * @Param: [Message]
     * @Return: void
     * @Author: Uniquek
     * @Date: 2020/8/9 5:02 下午
     */
    @StreamListener(Sink.INPUT)
    public void receive(String Message){
        log.info("收到的消息为:{}",Message);
    }
}
