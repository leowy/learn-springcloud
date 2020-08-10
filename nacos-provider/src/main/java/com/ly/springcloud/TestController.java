package com.ly.springcloud;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: Leowy Zhuang
 * @Date: 2020/8/10 16:16
 * @Description:
 */
@RequestMapping("/test")
@RestController
public class TestController {

    @GetMapping("/nacos-server")
    public String testNacos() {
        return "Hi, SpringCloud Alibaba Nacos ~";
    }
}
