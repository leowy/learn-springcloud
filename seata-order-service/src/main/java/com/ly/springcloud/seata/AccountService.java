package com.ly.springcloud.seata;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

/**
 * @Auther: Leowy Zhuang
 * @Date: 2020/8/17 15:33
 * @Description:
 */
@Service
public class AccountService {

    @Autowired
    RestTemplate restTemplate;


    public String decrease(@RequestParam("userId")Long userId, @RequestParam("money")BigDecimal money) {
        return restTemplate.getForObject("http://seata-account-service/account/decrease?userId={1}&money={2}", String.class, userId, money);

    }
}
