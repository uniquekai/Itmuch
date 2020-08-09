package com.itmuch.setvice.usercontentservice.rockermq;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * @ClassName : MySink
 * @Description :TODO <springcloud alibaba stream 测试自定义消息消费接口>
 * @Author : Uniquek
 * @Date: 2020-08-09 17:31
 */
public interface MySink {

    String MY_INPUT = "my-input";

    @Input(MY_INPUT)
    SubscribableChannel input();
}
