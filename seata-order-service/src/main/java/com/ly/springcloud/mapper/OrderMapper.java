package com.ly.springcloud.mapper;

import com.ly.springcloud.seata.Order;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * @Auther: Leowy Zhuang
 * @Date: 2020/8/17 14:19
 * @Description:
 */
public interface OrderMapper {

    /*创建订单*/

    @Insert("insert into `order`(user_id,product_id,count,money,status)" +
            " values( #{order.userId}, #{order.productId}, #{order.count}, #{order.money}, #{order.status})")
    void create(@Param("order") Order order);

    /*更新订单*/
    @Update("update `order` set status = #{status} where user_id = #{userId}")
    void update(@Param("userId") Long userId, @Param("status") Integer status);
}
