package com.ly.springcloud.seata;

import com.ly.springcloud.mapper.OrderMapper;
import io.seata.spring.annotation.GlobalTransactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Auther: Leowy Zhuang
 * @Date: 2020/8/17 15:28
 * @Description:
 */
@Service
public class OrderServiceImpl implements OrderService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderServiceImpl.class);
    @Autowired(required = false)
    OrderMapper orderMapper;
    @Autowired
    StorageService storageService;
    @Autowired
    AccountService accountService;


    @GlobalTransactional//(name = "my_test_tx_group", rollbackFor = Exception.class)
    @Override
    public void createOrder(Order order) {
        LOGGER.info("------->下单开始");
        //本应用创建订单
        orderMapper.create(order);

        //远程调用库存服务扣减库存
        LOGGER.info("------->order-service中扣减库存开始");
        storageService.decrease(order.getProductId(),order.getCount());
        LOGGER.info("------->order-service中扣减库存结束");

        //远程调用账户服务扣减余额
        LOGGER.info("------->order-service中扣减余额开始");
        accountService.decrease(order.getUserId(),order.getMoney());
        LOGGER.info("------->order-service中扣减余额结束");

        //修改订单状态为已完成
        LOGGER.info("------->order-service中修改订单状态开始");
        orderMapper.update(order.getUserId(),0);
        LOGGER.info("------->order-service中修改订单状态结束");

        LOGGER.info("------->下单结束");

    }
}
