package com.ly.springcloud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @Auther: Leowy Zhuang
 * @Date: 2020/8/10 16:30
 * @Description:
 */
@RequestMapping("/test")
@RestController
public class TestController {

    @LoadBalanced
    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/nacos-consumer")
    public String testNacos() {
        return restTemplate.getForObject("http://nacos-provider/test/nacos-server", String.class);
    }
}
