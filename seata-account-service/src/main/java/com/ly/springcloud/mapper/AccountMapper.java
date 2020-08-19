package com.ly.springcloud.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.math.BigDecimal;

/**
 * @Auther: Leowy Zhuang
 * @Date: 2020/8/17 16:35
 * @Description:
 */
public interface AccountMapper {

    @Update("update `account` set residue = #{money} where user_id = #{userId}")
    void decrease(@Param("userId") Long userId, @Param("money") BigDecimal money);
}
