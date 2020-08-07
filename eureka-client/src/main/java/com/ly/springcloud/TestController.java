package com.ly.springcloud;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

/**
 * @Auther: Leowy Zhuang
 * @Date: 2020/8/7 12:59
 * @Description:
 */
@RestController
public class TestController {
    @GetMapping("/test")
    public String test1() {
        System.out.println("I'm here~");
        return "hello, spring cloud ~";
    }

    @GetMapping("/test2")
    public List<String> test2(@RequestParam("id") String id) {
        System.out.println("I'm here~");
        String[] var = id.split(",");
        return Arrays.asList(var);
    }

    @GetMapping("/test3")
    public String test3(@RequestParam("id") String id) {
        System.out.println("I'm here~");
        return id;
    }
}
