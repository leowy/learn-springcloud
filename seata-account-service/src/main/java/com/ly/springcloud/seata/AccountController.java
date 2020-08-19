package com.ly.springcloud.seata;

import com.ly.springcloud.mapper.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/**
 * @Auther: Leowy Zhuang
 * @Date: 2020/8/17 16:37
 * @Description:
 */
@RestController
public class AccountController {

    @Autowired(required = false)
    private AccountMapper accountMapper;

    @GetMapping("/account/decrease")
    public String decrease(@RequestParam("userId") Long userId, @RequestParam("money") BigDecimal money) {

        System.out.println("userId:" + userId);
        System.out.println("money:" + money);
//        try {
//            TimeUnit.SECONDS.sleep(60);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        accountMapper.decrease(userId, money);

        return "success";
    }
}
