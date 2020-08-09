package com.itmuch.service.contentcenter.controller;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * @ClassName : MySource
 * @Description :TODO <springcloud alibaba stream 消息编程模型：自定义的消息生产接口>
 * @Author : Uniquek
 * @Date: 2020-08-09 17:26
 */
public interface MySource {
    String MY_OUTPUT = "my-output";

    @Output(MY_OUTPUT)
    MessageChannel output();
}
