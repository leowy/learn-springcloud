package com.ly.springcloud;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.rmi.runtime.Log;

/**
 * @Auther: Leowy Zhuang
 * @Date: 2020/8/10 13:15
 * @Description:
 */
@RestController
public class TestController {
    Logger logger = LoggerFactory.getLogger(getClass());

    @GetMapping("/sleuth")
    public String testSleuth() {
        logger.info("================>this is /sleuth ");
        return "Hi, sleuth~";
    }

}
