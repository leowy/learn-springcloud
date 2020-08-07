package com.ly.springcloud;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: Leowy Zhuang
 * @Date: 2020/8/7 23:27
 * @Description:
 */
@RestController
public class TestController {

    @GetMapping("/local/test")
    public String zuulTest() {
        return "hi, this is zuul service~";
    }
}
