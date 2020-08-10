# Hystrix DashBoard

### hystrix.stream端点404问题

+ 依赖引入问题
        
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>
        
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-hystrix</artifactId>
        </dependency>
        
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-hystrix-dashboard</artifactId>
        </dependency>
+ Hystrix启用问题
        
        @EnableHystrixDashboard注解
        确认启动类是否引入了@EnableHystrixDashboard注解。
        
        @EnableCircuitBreaker注解
        确认启动类是否添加了@EnableCircuitBreaker注解。这个是忽略比较多的一项重要原因。
        
        @EnableHystrix
        如果项目基于ribbon，确认启动类是否添加了@EnableHystrix注解。
        
        feign开启hystrix
        feign.hystrix.enabled是否为true。需要注意的是，此参数不会影响端点创建，只会影响feign的熔断降级功能是否开启。
+ actuator配置问题
        
        management:
          endpoints:
            web:
              exposure:
                include: hystrix.stream
        或者
        management:
          endpoints:
            web:
              exposure:
                include: '*'
