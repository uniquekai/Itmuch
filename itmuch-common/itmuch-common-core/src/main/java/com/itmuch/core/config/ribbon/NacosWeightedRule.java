package com.itmuch.core.config.ribbon;

import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;
import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.BaseLoadBalancer;
import com.netflix.loadbalancer.Server;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.alibaba.nacos.NacosDiscoveryProperties;
import org.springframework.cloud.alibaba.nacos.ribbon.NacosServer;

/**
 * 实现基于Nacos权重的负载均衡算法
 */

@Slf4j
public class NacosWeightedRule extends AbstractLoadBalancerRule {

    @Autowired
    private NacosDiscoveryProperties nacosDiscoveryProperties;

    @Override
    public void initWithNiwsConfig(IClientConfig iClientConfig) {
        //读取配置文件，并初始化NacosWeightedRule，基本留空
    }

    @Override
    public Server choose(Object o) {
        try {
            //LoadBalancer是Ribbon的入口，因为需要一个叫getName的api，但LoadBalancer里面没有，所以需要强转为BaseLoadBalancer
            //ILoadBalancer loadBalancer = this.getLoadBalancer();
            BaseLoadBalancer loadBalancer = (BaseLoadBalancer) this.getLoadBalancer();
            //想要请求的微服务的名称
            String name = loadBalancer.getName();
            //拿到服务发现的相关API
            NamingService namingService = nacosDiscoveryProperties.namingServiceInstance();
            //nacos client自动通过基于权重的负载均衡算法，给我们选择一个实例
            Instance instance = namingService.selectOneHealthyInstance(name);
            log.info("选择的实例是： prot = {} instance = {}", instance.getPort(), instance);
            return new NacosServer(instance);
        } catch (Exception e) {
            return null;
        }
    }
}
