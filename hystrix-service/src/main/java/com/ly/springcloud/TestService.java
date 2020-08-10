package com.ly.springcloud;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCollapser;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheRemove;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

/**
 * @Auther: Leowy Zhuang
 * @Date: 2020/8/7 11:24
 * @Description:
 */
@Service
public class TestService {

    @Autowired
    RestTemplate restTemplate;

    @LoadBalanced
    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }


    @HystrixCommand(fallbackMethod = "defaultMethod") //降级方法调用
    public String hystrixTest1() {
        return restTemplate.getForObject("http://EUREKA-CLIENT/test/test", String.class);
    }

    //降级方法
    public String defaultMethod() {
        return "hystrix service 服务已降级~";
    }


    // 请求合并
    @HystrixCollapser(batchMethod = "findAll",
            //所有线程的请求中的多次服务请求进行合并
            scope = com.netflix.hystrix.HystrixCollapser.Scope.GLOBAL,
            //默认，对一次请求的多次服务调用合并
            //scope = com.netflix.hystrix.HystrixCollapser.Scope.REQUEST,
            collapserProperties = {  @HystrixProperty(name = "timerDelayInMilliseconds", value = "1000")
            })
    public String hystrixTest2(String id) {
        System.out.println("this is hystrixTestCommand");
        return null;
    }

    @HystrixCommand(fallbackMethod = "errorMethod")
    public List<String> findAll(List<String> ids) {
        System.out.println("findAll" + ids);
        List<String> result = restTemplate.getForObject("http://EUREKA-CLIENT/test/test2?id={1}", List.class, String.join(",",ids));
        return result;
    }

    // 请求合并降级方法
    private List<String> errorMethod(List<String> ids) {
        List<String> result = new ArrayList<>(ids.size());
        for (int i = 0; i < ids.size(); i++) {
            result.add("hystrix service 服务已降级~");
        }
        return result;
    }


    @CacheResult(cacheKeyMethod = "getHystrixTest3Key")
    @HystrixCommand(fallbackMethod = "error2Method" , commandKey = "getCache") //降级方法调用
    public String hystrixTest3(String id) {
        System.out.println(id + "=>" + id);
        return restTemplate.getForObject("http://EUREKA-CLIENT/test/test3?id={1}", String.class, id);
    }

    private String getHystrixTest3Key(String id) {
        return id;
    }

    @CacheRemove(cacheKeyMethod = "getHystrixTest3Key",commandKey = "getCache" )
    @HystrixCommand(fallbackMethod = "error2Method" )
    public String hystrixTest4(String id) {
        System.out.println(id + "=>" + id);
        return restTemplate.getForObject("http://EUREKA-CLIENT/test3?id={1}", String.class, id);
    }

    private String error2Method(String id) {
        return "服务已降级";
    }

}
