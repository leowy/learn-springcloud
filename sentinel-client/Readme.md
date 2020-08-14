# Sentinel: 分布式系统的流量哨兵

### Sentinel 是什么？
随着微服务的流行，服务和服务之间的稳定性变得越来越重要。Sentinel 以流量为切入点，从流量控制、熔断降级、系统负载保护等多个维度保护服务的稳定性。  

Sentinel 具有以下特征:  
+ **丰富的应用场景：**
Sentinel 承接了阿里巴巴近 10 年的双十一大促流量的核心场景，例如秒杀（即突发流量控制在系统容量可以承受的范围）、消息削峰填谷、集群流量控制、实时
熔断下游不可用应用等。
+ **完备的实时监控：**
Sentinel 同时提供实时的监控功能。您可以在控制台中看到接入应用的单台机器秒级数据，甚至 500 台以下规模的集群的汇总运行情况。
+ **广泛的开源生态：**
Sentinel 提供开箱即用的与其它开源框架/库的整合模块，例如与 Spring Cloud、Dubbo、gRPC 的整合。您只需要引入相应的依赖并进行简单的配置即可快速
地接入 Sentinel。
+ **完善的SPI 扩展点：**
Sentinel 提供简单易用、完善的 SPI 扩展接口。您可以通过实现扩展接口来快速地定制逻辑。例如定制规则管理、适配动态数据源等。

Sentinel 的主要特性：
![aL1H5d.png](https://s1.ax1x.com/2020/08/11/aL1H5d.png)

### sentinel dashboard 安装部署
直接下载安装使用[sentinel dashboard](https://github.com/alibaba/Sentinel/releases/tag/1.7.2)
        
        java -jar sentinel-dashboard-XXX.jar

调整参数：
+ -Dserver.port=8090
+ -Dsentinel.dashboard.auth.username=sentinel 默认账号：sentinel
+ -Dsentinel.dashboard.auth.password=123456 默认密码：sentinel
+ -Dserver.servlet.session.timeout=7200

### 整合sentinel 

+ 依赖
        
        <dependency>
            <groupId>com.alibaba.cloud</groupId>
            <artifactId>spring-cloud-starter-alibaba-sentinel</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>com.alibaba.cloud</groupId>
            <artifactId>spring-cloud-starter-alibaba-nacos-discovery</artifactId>
        </dependency>
+ 配置
        
        server:
            port: 10050
        spring:
            application:
                name: sentinel-client
            cloud:
                sentinel:
                    transport:
                        # sentinel dashboard 地址
                        dashboard: localhost:8080
                nacos:
                    discovery:
                        # nacos注册中心地址
                        server-addr: 192.168.4.105:8848

+ rest接口
        
        见 TestController.java
        
### 流控规则
+ 资源名：对应@SentinelResource注解value值，默认为路径名
+ 针对来源： 服务名，默认为default（不区分服务）
+ 阈值类型： QPS（Queries per second）/线程数
+ 是否集群（单机阈值/集群阈值<单机均摊/总体阈值>）
+ 流控模式
    + 直接： 当服务达到限流条件，直接限流
    + 关联： 填写关联资源，即服务名，关联资源到达限流条件，开始限流自身
    + 链路： 填写入口资源，即服务名，当该条链路到达限流条件，开始限流自身
+ 流控效果
    + 快速失败： 直接响应失败
    + Warm up： codeFactor（冷加载因子，默认为3），阈值/codeFactor，经过预热时长，到达阈值
    + 排队等待： 到达QPS阈值后，匀速通过请求
### 降级规则
+ 资源名： 同上
+ 降级策略：
    + RT：秒级别 请求响应时长（RT默认最大响应时长4900ms,可手动配置最大值-Dcsp.sentinel.statistic.max.rt=5000）
    + 异常比例： 秒级别 异常比例（0.0~1.0）
    + 异常数： 分钟级别
+ 时间窗口：满足降级策略后，降级生效时长
### 热点规则
+ 资源名： 同上
+ 限流模式： QPS（不可选）
+ 参数索引： 0开始（表示第1个参数）
+ 单机阈值/均摊阈值：
+ 是否集群：
+ 例外项：
    + 参数类型：
    + 参数值：
    + 限流阈值：
### 系统规则
+ 阈值类型：
    + LOAD： 仅对 Linux/Unix-like 机器生效，设定参考值一般是 CPU cores * 2.5
    + RT： 当单台机器上所有入口流量的平均 RT 达到阈值即触发系统保护，单位是毫秒。
    + 线程数： 当单台机器上所有入口流量的并发线程数达到阈值即触发系统保护。
    + 入口QPS：当单台机器上所有入口流量的 QPS 达到阈值即触发系统保护
    + CPU使用率： 当系统 CPU 使用率超过阈值即触发系统保护（取值范围 0.0-1.0），比较灵敏
+ 阈值： 
### 授权规则
+ 资源名： 同上
+ 流控应用：调用方服务名，多个服务逗号分隔
+ 授权类型：
    + 白名单
    + 黑名单
### 集群流控 
+ 新增Token Server
    + 机器类型：（应用内机器/外部指定机器）
    + 选择机器
    + Server端口
    + 最大允许QPS

### 规则持久化
[![aOmLqJ.png](https://s1.ax1x.com/2020/08/11/aOmLqJ.png)](https://imgchr.com/i/aOmLqJ)
        
+ 依赖

        <dependency>
            <groupId>com.alibaba.csp</groupId>
            <artifactId>sentinel-datasource-nacos</artifactId>
        </dependency>
+ 配置
        
        spring:
            application:
                name: sentinel-client
            cloud:
                sentinel:
                    transport:
                        # sentinel dashboard 地址
                        dashboard: localhost:8080
                    # 规则持久化
                    datasource:
                        ds:
                            nacos:
                                server-addr: 192.168.4.105:8848
                                group-id: DEFAULT_GROUP
                                rule-type: flow
                                data-id: sentinel-rule
                                ###  读取培训文件类型为json
                                data-type: json

备注： 使用以上方法，nacos配置中心可以实时同步规则，但是sentinel-dashboard无法同步规则到配置中心


### @SentinelResource注解
+ `value`: 资源名称，必填
+ `entryType`： entry类型，可选（默认：EntryType.OUT）
+ `blockHandler`/`blockHandlerClass`:blockHandler / blockHandlerClass: blockHandler对应处理 BlockException 的函数名称，可选项。
blockHandler 函数访问范围需要是 public，返回类型需要与原方法相匹配，参数类型需要和原方法相匹配并且最后加一个额外的参数，类型为 BlockException。
blockHandler 函数默认需要和原方法在同一个类中。若希望使用其他类的函数，则可以指定 blockHandlerClass 为对应的类的 Class 对象，注意对应的函数
必需为 static 函数，否则无法解析。
+ `fallback`: fallback 函数名称，可选项，用于在抛出异常的时候提供 fallback 处理逻辑。fallback 函数可以针对所有类型的异常（除了
exceptionsToIgnore里面排除掉的异常类型）进行处理。fallback 函数签名和位置要求：
    + 返回值类型必须与原函数返回值类型一致；
    + 方法参数列表需要和原函数一致，或者可以额外多一个 Throwable 类型的参数用于接收对应的异常。
    + fallback 函数默认需要和原方法在同一个类中。若希望使用其他类的函数，则可以指定 fallbackClass 为对应的类的 Class 对象，注意对应的函数
    必需为 static 函数，否则无法解析。
+ `defaultFallback`: 默认的 fallback 函数名称，可选项，通常用于通用的 fallback 逻辑（即可以用于很多服务或方法）。默认 fallback 函数可以针
对所有类型的异常（除了exceptionsToIgnore里面排除掉的异常类型）进行处理。若同时配置了 fallback 和 defaultFallback，则只有 fallback 会
生效。defaultFallback 函数签名要求：
    + 返回值类型必须与原函数返回值类型一致；
    + 方法参数列表需要为空，或者可以额外多一个 Throwable 类型的参数用于接收对应的异常。
    + defaultFallback 函数默认需要和原方法在同一个类中。若希望使用其他类的函数，则可以指定 fallbackClass 为对应的类的 Class 对象，注意对应
    的函数必需为 static 函数，否则无法解析。
+ exceptionsToIgnore: 用于指定哪些异常被排除掉，不会计入异常统计中，也不会进入 fallback 逻辑中，而是会原样抛出。

备注： 若 `blockHandler` 和 `fallback` 都进行了配置，则被限流降级而抛出 `BlockException` 时只会进入 `blockHandler` 处理逻辑。若未配置 
`blockHandler`、`fallback` 和 `defaultFallback`，则被限流降级时会将 `BlockException` 直接抛出。

### 官方文档
[![github](https://img.shields.io/badge/sentinel-1.7.2-green)](https://github.com/alibaba/Sentinel/wiki/%E4%BB%8B%E7%BB%8D)