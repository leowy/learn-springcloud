package com.ly.springcloud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: Leowy Zhuang
 * @Date: 2020/8/7 15:39
 * @Description:
 */
@RestController
public class TestController {

    @Autowired
    TestService testService;

    @GetMapping("/feign1")
    public String feign1() {
        return testService.feignTest1();
    }

    @GetMapping("/feign2")
    public String feign2(@RequestParam("id") String id) {
        return testService.feignTest2(id);
    }

    @GetMapping("/feign3")
    public String feign3(@RequestParam("id") String id) {
        return testService.feignTest3(id);
    }
}
