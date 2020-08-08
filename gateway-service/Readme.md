# SpringCloud Gateway

### 介绍
SpringCloud Gateway 作为 Spring Cloud 生态系统中的网关，目标是替代 Zuul，在Spring Cloud 2.0以上版本中，没有对新版本的Zuul 2.0以上最新
高性能版本进行集成，仍然还是使用的Zuul 2.0之前的非Reactor模式的老版本。而为了提升网关的性能，SpringCloud Gateway是基于WebFlux框架实现的，而
WebFlux框架底层则使用了高性能的Reactor模式通信框架Netty。

### 概念
+ Route(路由)
        
        路由是构建网关的基本模块，它由ID，目标URI，一系列的断言和过滤器组成，如果断言为true则匹配该路由
+ Predicate(断言)
        
        指的是Java 8 的 Function Predicate。 输入类型是Spring框架中的ServerWebExchange。 这使开发
        人员可以匹配HTTP请求中的所有内容，例如请求头或请求参数。如果请求与断言相匹配，则进行路由
+ Filter(过滤器)
        
        指的是Spring框架中GatewayFilter的实例，使用过滤器，可以在请求被路由前后对请求进行修改

![aI7u3q.png](https://s1.ax1x.com/2020/08/08/aI7u3q.png)