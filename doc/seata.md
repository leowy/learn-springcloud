# 分布式事务seata
[![github](https://img.shields.io/badge/seata-1.3.0-blue.svg)]()

### 部署指南
[seata部署指南](http://seata.io/zh-cn/docs/ops/deploy-guide-beginner.html)
### 测试准备
+ 环境准备 mysql、nacos、seata
+ 导入测试需要的脚本  
[测试需要的脚本以及文件](https://github.com/seata/seata/tree/1.3.0/script)
+ 注册中心
+ 配置中心

### 说明
+ 业务中registry.conf配置与application.yml中seata开头的配置相等
+ 出现下面这种错误有几种情况 
`i.s.c.r.netty.NettyClientChannelManager : no available service 'null' found, please make sure registry config correct`  
    + springcloud引入seata时，默认的seata-spring-boot-starter是1.1.0，该错会导致如下问题：   
    解决方法1：
            
            <dependency>
                <groupId>io.seata</groupId>
                <artifactId>seata-spring-boot-starter</artifactId>
                <version>1.3.0</version>
            </dependency>
            <dependency>
                <groupId>com.alibaba.cloud</groupId>
                <artifactId>spring-cloud-starter-alibaba-seata</artifactId>
                <exclusions>
                    <exclusion>
                        <groupId>io.seata</groupId>
                        <artifactId>seata-spring-boot-starter</artifactId>
                    </exclusion>
                </exclusions>
            </dependency>
    + 配置问题：
            
            注意事务群组

[seata参数说明](http://seata.io/zh-cn/docs/user/configurations.html)

+ 测试使用模块
    + seata-account-service
    + seata-order-service
    + seata-storage-service
