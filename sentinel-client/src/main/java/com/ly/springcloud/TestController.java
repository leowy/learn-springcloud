package com.ly.springcloud;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.aspectj.apache.bcel.classfile.Module;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @Auther: Leowy Zhuang
 * @Date: 2020/8/11 09:05
 * @Description:
 */
@RequestMapping("/test")
@RestController
public class TestController {

    @GetMapping("/sentinel")
    public String testSentinel() {
        return "Hi, Sentinel ~";
    }

    @SentinelResource(value = "sentinel1", blockHandler = "blockHandler",fallback = "fallback")
    @GetMapping("/sentinel1")
    public String testSentinel1() {
        int i = 10 / 0;
        return "Hi, Exception";
    }

    // 降级规则（RT模式 1000ms ，时间窗口1s）
    @SentinelResource(value = "sentinel2", blockHandler = "blockHandler")
    @GetMapping("/sentinel2")
    public String testSentinel2() {
        try {
            TimeUnit.SECONDS.sleep(2L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "Hi, Timeout ~";
    }

    @SentinelResource(value = "sentinel3",blockHandler = "blockHandler")
    @GetMapping("/sentinel3")
    public String testSentinel3(@RequestParam(value = "id",required = false) String id,
                            @RequestParam(value="name",required = false) String name) {
        return "Hi, id is " + id + ", name is " + name;
    }

    public static String blockHandler(String id, String name, BlockException e) {
        return "服务已降级";
    }

    public static String blockHandler(BlockException e) {
        return "服务已降级";
    }

    public static String fallback() {
        return "fallback";
    }
}
