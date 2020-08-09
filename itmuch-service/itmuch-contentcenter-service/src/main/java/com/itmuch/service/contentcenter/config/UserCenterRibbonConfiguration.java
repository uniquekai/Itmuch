package com.itmuch.service.contentcenter.config;

import org.springframework.cloud.netflix.ribbon.RibbonClients;
import org.springframework.context.annotation.Configuration;
import ribbonconfiguration.RibbonConfiguration;

/**
 * Created with IntelliJ IDEA.
 * User: uniquek
 * Date: 2019/12/26
 * Time: 8:04 下午
 * Description: Ribbon的负载均衡配置类
 */

@Configuration
//指定服务的负载均衡配置
//@RibbonClient(name = "user-center",configuration = RibbonConfiguration.class)
//全局的负载均衡配置
@RibbonClients(defaultConfiguration = RibbonConfiguration.class)
public class UserCenterRibbonConfiguration {

}
