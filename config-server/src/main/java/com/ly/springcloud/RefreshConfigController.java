package com.ly.springcloud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @Auther: Leowy Zhuang
 * @Date: 2020/8/9 20:37
 * @Description:  github直接设置webhooks的URL会报错：如下
 *<p>
 * w.s.m.s.DefaultHandlerExceptionResolver : Resolved [org.springframework.http.converter.HttpMessageNotReadableException:
 * JSON parse error: Cannot deserialize instance of `java.lang.String` out of START_OBJECT token; nested exception is
 * com.fasterxml.jackson.databind.exc.MismatchedInputException: Cannot deserialize instance of `java.lang.String` out of
 * START_OBJECT token at [Source: (PushbackInputStream); line: 1, column: 141] (through reference chain:
 * java.util.LinkedHashMap["repository"])]
 * </p>
 *
 * 无法排除原因，故采取内部调用办法
 */
@RestController
public class RefreshConfigController {

    @Autowired
    RestTemplate restTemplate;

    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
    @PostMapping("/refresh")
    public void refresh() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.CONTENT_TYPE, "application/json");
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(null,httpHeaders);
        restTemplate.postForObject("http://localhost:9060/actuator/bus-refresh", request,String.class);
    }


}
