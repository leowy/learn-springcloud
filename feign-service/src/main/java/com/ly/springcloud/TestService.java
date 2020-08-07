package com.ly.springcloud;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Auther: Leowy Zhuang
 * @Date: 2020/8/7 15:48
 * @Description:
 */
@FeignClient(value = "EUREKA-CLIENT", fallback = FallbackService.class)
public interface TestService {

    @GetMapping("/test")
    String feignTest1();

    @GetMapping("/test2")
    String feignTest2(@RequestParam("id") String id);

    @GetMapping("/test3")
    String feignTest3(@RequestParam("id") String id);
}
