package com.ly.springcloud;

import org.springframework.stereotype.Component;

/**
 * @Auther: Leowy Zhuang
 * @Date: 2020/8/7 16:03
 * @Description:
 */
@Component
public class FallbackService implements TestService{
    @Override
    public String feignTest1() {
        return "服务已降级";
    }

    @Override
    public String feignTest2(String id) {
        return "服务已降级";
    }

    @Override
    public String feignTest3(String id) {
        return "服务已降级";
    }
}
