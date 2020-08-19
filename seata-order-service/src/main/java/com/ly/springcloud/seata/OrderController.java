package com.ly.springcloud.seata;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/**
 * @Auther: Leowy Zhuang
 * @Date: 2020/8/17 14:56
 * @Description:
 */
@RestController
public class OrderController {

    @Autowired
    OrderService orderService;

    @GetMapping("/createOrder")
    public void createOrder(@RequestParam("userId")Long userId,@RequestParam("productId")Long productId,
                            @RequestParam("count")int count,@RequestParam("money")BigDecimal money,
                            @RequestParam("status")int status) {
        Order order = new Order();
        order.setUserId(userId);
        order.setProductId(productId);
        order.setCount(count);
        order.setMoney(money);
        order.setStatus(status);

        orderService.createOrder(order);
    }

}
