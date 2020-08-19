package com.ly.springcloud.seata;

import com.ly.springcloud.mapper.StorageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: Leowy Zhuang
 * @Date: 2020/8/17 16:10
 * @Description:
 */
@RestController
public class StorageController {

    private StorageMapper storageMapper;

    @GetMapping("/storage/decrease")
    public String decrease(@RequestParam("productId") Long productId, @RequestParam("residue") int residue) {
        System.out.println("start===============>");
        System.out.println("productId:" + productId);
        System.out.println("residue:" + residue);
        storageMapper.decrease(productId, residue);
        System.out.println("end=================>");
        return "success";
    }

    @GetMapping("/storage/test")
    public String test() {
        return "success";
    }

    @Autowired(required = false)
    public void setStorageMapper(StorageMapper storageMapper) {
        this.storageMapper = storageMapper;
    }
}
