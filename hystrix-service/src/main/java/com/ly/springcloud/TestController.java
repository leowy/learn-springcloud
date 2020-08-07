package com.ly.springcloud;

import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @Auther: Leowy Zhuang
 * @Date: 2020/8/7 13:01
 * @Description:
 */
@RestController
public class TestController {
    @Autowired
    TestService userService;


    @GetMapping("/hystrix1")
    public String test1() {
        return userService.hystrixTest1();
    }

    @GetMapping("/hystrix2")
    public String test2(@RequestParam("id") String id) throws ExecutionException, InterruptedException {
        return userService.hystrixTest2(id);
    }

    @GetMapping("/hystrix3")
    public String test3(@RequestParam("id") String id) {
//        HystrixRequestContext context = HystrixRequestContext.initializeContext();
        String result = userService.hystrixTest3(id);
        String result2 = userService.hystrixTest3(id);
        String result3 = userService.hystrixTest3(id);
//        context.close();
        return result;
    }

    @GetMapping("/hystrix4")
    public String test4(@RequestParam("id") String id) {
        String result = userService.hystrixTest3(id);
        String result2 = userService.hystrixTest4(id);
        String result3 = userService.hystrixTest3(id);
        return result;
    }
}
