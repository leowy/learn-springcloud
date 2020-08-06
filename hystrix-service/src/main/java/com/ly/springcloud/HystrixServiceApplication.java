package com.ly.springcloud;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@EnableCircuitBreaker
@EnableDiscoveryClient
@SpringBootApplication
public class HystrixServiceApplication {

    @LoadBalanced
    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Autowired
    public RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "defaultMethod")
    @GetMapping("/hystrix")
    public String hystrixTest() {
        return restTemplate.getForObject("http://EUREKA-CLIENT/test", String.class);
    }

    public String defaultMethod() {
        return "hystrix service 服务已降级~";
    }


    public static void main(String[] args) {
        SpringApplication.run(HystrixServiceApplication.class, args);
    }

}
