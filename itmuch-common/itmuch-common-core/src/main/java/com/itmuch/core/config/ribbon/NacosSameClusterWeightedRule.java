package com.itmuch.core.config.ribbon;

import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;
import com.alibaba.nacos.client.naming.core.Balancer;
import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.BaseLoadBalancer;
import com.netflix.loadbalancer.Server;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.alibaba.nacos.NacosDiscoveryProperties;
import org.springframework.cloud.alibaba.nacos.ribbon.NacosServer;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 实现基于Nacos权重，实现同一集群优先调用
 */
@Slf4j
public class NacosSameClusterWeightedRule extends AbstractLoadBalancerRule {
    @Autowired
    private NacosDiscoveryProperties nacosDiscoveryProperties;

    @Override
    public void initWithNiwsConfig(IClientConfig iClientConfig) {

    }

    @Override
    public Server choose(Object o) {

        try {
            //拿到该服务配置文件中的集群名称
            String clusterName = nacosDiscoveryProperties.getClusterName();
            BaseLoadBalancer loadBalancer = (BaseLoadBalancer) this.getLoadBalancer();
            //想要请求的微服务的名称
            String name = loadBalancer.getName();
            //拿到服务发现的相关API
            NamingService namingService = nacosDiscoveryProperties.namingServiceInstance();
            //1.找出指定服务的所有实例
            List<Instance> instances = namingService.selectInstances(name, true);
            //2.过滤出相同集群下的所有实例
            List<Instance> sameClustInstances = instances.stream()
                    .filter(instance -> Objects.equals(instance.getClusterName(), clusterName))
                    .collect(Collectors.toList());
            //3.如果相同集群下的实例为空，就用该服务下所有的实例
            List<Instance> instancesToBeChosen = new ArrayList<>();
            if (CollectionUtils.isEmpty(sameClustInstances)) {
                instancesToBeChosen = instances;
                log.warn("跨集群调用，name = {},clusterName  = {},intances = {}",
                        name,
                        clusterName,
                        instances
                );
            } else {
                instancesToBeChosen = sameClustInstances;
            }
            //4.基于权重的负载均衡算法
            Instance instance = ExtendBalancer.getHostByRandomWeight1(instancesToBeChosen);
            log.info("选择的实例是： prot = {} instance = {}", instance.getPort(), instance);
            return new NacosServer(instance);
        } catch (NacosException e) {
            log.info("设置nacos同一集群优先调用时发生异常:{}", e);
            return null;
        }

    }
}

class  ExtendBalancer extends Balancer {
    public static Instance getHostByRandomWeight1(List<Instance> hosts) {
        return getHostByRandomWeight(hosts);
    }
}