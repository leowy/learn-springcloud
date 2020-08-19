package com.ly.springcloud.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * @Auther: Leowy Zhuang
 * @Date: 2020/8/17 16:08
 * @Description:
 */
public interface StorageMapper {

    @Update("update `storage` set residue = #{residue} where product_id = #{productId}")
    void decrease(@Param("productId") Long productId, @Param("residue") Integer residue);
}
