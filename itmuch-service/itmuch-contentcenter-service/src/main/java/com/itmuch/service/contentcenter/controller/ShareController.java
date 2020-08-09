package com.itmuch.service.contentcenter.controller;


import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.baomidou.mybatisplus.extension.api.R;
import com.itmuch.api.contentcenterapi.feignclient.ShareFeignClient;
import com.itmuch.api.contentcenterapi.model.returnf.ShareInfoReturnModel;
import com.itmuch.common.mybatisplus.user.entity.User;
import com.itmuch.core.config.sentinel.BlockHandlerClass;
import com.itmuch.service.contentcenter.service.ShareService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


/**
 * Created with IntelliJ IDEA.
 * User: uniquek
 * Date: 2019/12/28
 * Time: 5:14 下午
 * Description:
 */
@Slf4j
@RestController
public class ShareController implements ShareFeignClient {
    private final ShareService shareService;

    public ShareController(ShareService shareService) {
        this.shareService = shareService;
    }

    /**
     * 功能描述:根据传入的id值返回一个笔记的信息模型队形
     * @param: [id]
     * @return: com.baomidou.mybatisplus.extension.api.R<com.itmuch.api.contentcenterapi.model.returnf.ShareInfoReturnModel>
     * @auther: uniquek
     * @date: 2020/2/12 10:26 下午
     */
    @Override
    public R<ShareInfoReturnModel> getShare(Integer id) {
        return R.ok(shareService.shareInfoById(id));
    }
    /**
     * 功能描述: 测试sentinelapi
     * @param: [a]
     * @return: java.lang.String
     * @auther: uniquek
     * @date: 2020/2/12 7:51 下午
     */
    @GetMapping("/share/test-sentinel-api")
    @SentinelResource(
            value = "test-sentinel-api",
            blockHandler = "block",
            blockHandlerClass = BlockHandlerClass.class,
            fallback = "fallback"
    )
    //blockHandler和fallback调用的方法都需要和调用的方法返回的参数和传递参数都要相同
    public String TestSentinelAPI(@RequestParam(required = false) String a){
        //下边写被保护的业务逻辑
        if (StringUtils.isBlank(a)){
            // 默认情况下sentinel只会统计BlockException类以及BlockException的子类。如果需要sentinel统计其他异常 则需要步骤1
            throw new IllegalArgumentException("a不能为空");
        }
        return a;
    }
    /**
     * 功能描述:保护的资源被降级。在sentinel1.6以后可以像blockHandlerClass一样指定专门的配置类
     * @param: [a]
     * @return: java.lang.String
     * @auther: uniquek
     * @date: 2020/2/12 10:24 下午
     */
    public String fallback( String a){
        return "被保护的资源被降级";
    }

    @Autowired
    private RestTemplate restTemplate;
    /**
     * 功能描述:测试为restTemplate整合sentinel
     * @param: [userid]
     * @return: com.itmuch.common.mybatisplus.user.entity.User
     * @auther: uniquek
     * @date: 2020/2/12 11:26 下午
     */
    @GetMapping("/share/test-restTemplate-sentinel/{userid}")
    public User test(@PathVariable Integer userid){
        log.info("传递的参数为 ：{}",userid);
        return this.restTemplate
                .getForObject(
                        "http://user-center/users/a/{userid}",
                        User.class,userid);
    }
    @Autowired
    private Source source;
    /**
     * 功能描述: TODO:<测试springcloud stream自带的消息生产接口>
     * @Param: []
     * @Return: java.lang.String
     * @Author: Uniquek
     * @Date: 2020/8/9 4:24 下午
     */
    @GetMapping("/test-stream")
    public String testStream(){
        this.source.output()
                .send(
                        MessageBuilder
                        .withPayload("消息体")
                        .build()
                );
        return "success";
    }
    @Autowired
    private MySource mySource;
    /**
     * 功能描述: TODO:<测试自定义消息生产接口>
     * @Param: []
     * @Return: java.lang.String
     * @Author: Uniquek
     * @Date: 2020/8/9 5:30 下午
     */
    @GetMapping("/test-my-stream")
    public String testMyStream(){
        this.mySource.output()
                .send(
                        MessageBuilder
                                .withPayload("测试自定义消息生产接口")
                                .build()
                );
        return "success";
    }
}