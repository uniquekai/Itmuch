# Itmuch 
项目介绍：项目基于SpringBoot、SpringCloud Alibaba,DB使用MySQL接入Mybatis Plus
分布式中间件包括：Nacos、Feign、Sentinel、RocketMQ、Gateway

项目启动：
### Nacos
* nacos 启动：bin下：sh startup.sh -m standalone
* nacos控制台：http://localhost:8848/nacos/index.html
### sentinel控制台
* sentinel控制台启动：执行sentinel控制台jar包
* 访问：http://localhost:8080/#/login
### rocketMQ
1. MQ启动：nohup sh bin/mqnamesrv &
    * 验证是否启动：tail -f ~/logs/rocketmqlogs/namesrv.log
    * 如果成功启动，能看到类似如下的日志：
    * 2019-07-18 17:03:56 INFO main - The Name Server boot success. ...
2. 启动 Broker
    * nohup sh bin/mqbroker -n localhost:9876 &
    * 验证是否启动OK：
    * tail -f ~/logs/rocketmqlogs/broker.log
    * 如果启动成功，能看到类似如下的日志：
    * 2019-07-18 17:08:41 INFO main - The broker[itmuchcomdeMacBook-Pro.local, 192.168.43.197:10911] boot success. serializeType=JSON and name server is localhost:9876
3. 以下为停止rocketMQ的操作
    * sh bin/mqshutdown broker
    * 输出如下信息说明停止成功
    * The mqbroker(36695) is running...
Send shutdown request to mqbroker(36695) OK
    * sh bin/mqshutdown namesrv
* 输出如下信息说明停止成功
The mqnamesrv(36664) is running...
Send shutdown request to mqnamesrv(36664) OK
4. MQ控制台：启动控制台jar包
    * http://localhost:17890/#
