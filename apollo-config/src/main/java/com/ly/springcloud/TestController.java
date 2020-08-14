package com.ly.springcloud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: Leowy Zhuang
 * @Date: 2020/8/14 15:32
 * @Description:
 */
@RequestMapping("/test")
@RestController
public class TestController {

    @Autowired
    AppConfig appConfig;

    @GetMapping("/conf")
    public ConfigBean getConfig() {
        return appConfig.configBean();
    }

}
