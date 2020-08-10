# sleuth

### 依赖
        # 引入sleuth
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-sleuth</artifactId>
        </dependency>
        # 整合zipkin需要
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-sleuth-zipkin</artifactId>
        </dependency>

### 服务配置
        spring:
            zipkin:
                base-url: http://localhost:9411
            sleuth:
              sampler:
                  # 采样率
                  probability: 1
                
### zipkin说明
        
SpringBoot 2.X以后不需要搭建服务端，可以直接下载使用
[zipkin服务端](https://repo1.maven.org/maven2/io/zipkin/java/zipkin-server/2.12.9/zipkin-server-2.12.9-exec.jar)

### sleuth日志说明

[sleuth-service,db1db321ba325371,db1db321ba325371,false]   
其中:
+ sleuth-service: 服务名;也就是spring.application.name
+ db1db321ba325371: traceId;它用来标识一条请求链路<一条请求链路包含一个traceId，多个spanId>
+ db1db321ba325371: spanId;它表示一个工作单元
+ false:    表示是否将该信息输出到zipkin等服务中来收集和展示
      