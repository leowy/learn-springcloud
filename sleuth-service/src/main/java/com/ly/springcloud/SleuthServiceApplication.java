package com.ly.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class SleuthServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SleuthServiceApplication.class, args);
    }

}
