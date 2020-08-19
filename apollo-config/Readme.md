# apollo

### apollo服务端

apollo服务端分为3个安装包：apollo-configservice、apollo-adminservice、apollo-portal
![d9x1m9.png](https://s1.ax1x.com/2020/08/14/d9x1m9.png)  
上图简要描述了Apollo的总体设计，我们可以从下往上看：

+ `Config Service`提供配置的`读取`、`推送`等功能，服务对象是`Apollo客户端`
+ `Admin Service`提供配置的`修改`、`发布`等功能，服务对象是`Apollo Portal`（管理界面）
+ `Config Service`和`Admin Service`都是**多实例、无状态部署**，所以需要将自己注册到Eureka中并保持心跳
+ 在Eureka之上我们架了一层`Meta Server`用于封装Eureka的服务发现接口
+ Client通过域名访问`Meta Server`获取`Config Service`服务列表（IP+Port），而后直接通过`IP+Port`访问服务，同时在Client侧会做`load balance`、错误重试
+ Portal通过域名访问`Meta Server`获取`Admin Service`服务列表（IP+Port），而后直接通过`IP+Port`访问服务，同时在Portal侧会做`load balance`、错误重试
+ 为了简化部署，我们实际上会把`Config Service`、`Eureka`和`Meta Server`三个逻辑角色部署在同一个JVM进程中

具体设计：[Apollo配置中心设计](https://github.com/ctripcorp/apollo/wiki/Apollo%E9%85%8D%E7%BD%AE%E4%B8%AD%E5%BF%83%E8%AE%BE%E8%AE%A1)
安装部署：[分布式部署指南](https://github.com/ctripcorp/apollo/wiki/%E5%88%86%E5%B8%83%E5%BC%8F%E9%83%A8%E7%BD%B2%E6%8C%87%E5%8D%97)
数据库脚本：[数据库脚本](https://github.com/ctripcorp/apollo/tree/master/scripts/sql)

#### 注意事项：
+ 部署顺序： config、admin、portal
+ 数据库`apolloconfigdb`中`serverconfig`表`eureka.service.url`配置注册中心地址，只能手动修改，portal改不了该库数据
+ config、admin使用的是apolloconfigdb库，portal使用的是apolloportaldb库
+ 如需要config、admin、portal的监听端口，则需要修改对应服务的启动脚本startup.sh


### apollo客户端

[![dCSGRK.md.png](https://s1.ax1x.com/2020/08/14/dCSGRK.md.png)](https://imgchr.com/i/dCSGRK)

#### Apollo客户端的实现原理：

+ 客户端和服务端保持了一个长连接，从而能第一时间获得配置更新的推送。（通过Http Long Polling实现）
+ 客户端还会定时从Apollo配置中心服务端拉取应用的最新配置。
    + 这是一个fallback机制，为了防止推送机制失效导致配置不更新
    + 客户端定时拉取会上报本地版本，所以一般情况下，对于定时拉取的操作，服务端都会返回304 - Not Modified
    + 定时频率默认为每5分钟拉取一次，客户端也可以通过在运行时指定System Property: apollo.refreshInterval来覆盖，单位为分钟。
+ 客户端从Apollo配置中心服务端获取到应用的最新配置后，会保存在内存中
+ 客户端会把从服务端获取到的配置在本地文件系统缓存一份
    + 在遇到服务不可用，或网络不通的时候，依然能从本地恢复配置
+ 应用程序可以从Apollo客户端获取最新的配置、订阅配置更新通知

[Java客户端使用指南](https://github.com/ctripcorp/apollo/wiki/Java%E5%AE%A2%E6%88%B7%E7%AB%AF%E4%BD%BF%E7%94%A8%E6%8C%87%E5%8D%97)

+ 引入依赖
        
        <dependency>
            <groupId>com.ctrip.framework.apollo</groupId>
            <artifactId>apollo-client</artifactId>
            <version>1.7.0</version>
        </dependency>
        
+ 配置
        
        # 对应apollo 服务端中的AppId
        app.id: zhangw-test
        apollo:
          # meta server 地址
          meta: http://192.168.4.105:8088
          # 与注解@EnableApolloConfig({"application","develop.demo"})意思相同，二选一
          # 文件内容覆盖顺序，相同字段以靠前的文件字段优先
        #  bootstrap:
        #    enabled: true
        #    namespaces: application,develop.demo