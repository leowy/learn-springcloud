package com.ly.springcloud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: Leowy Zhuang
 * @Date: 2020/8/10 16:59
 * @Description:
 */
@RequestMapping("test")
@RestController
public class TestController {

    @Autowired
    UserConfig userConfig;

    @GetMapping("/nacos-config")
    public String testNacos() {
        return userConfig.toString();
    }
}
