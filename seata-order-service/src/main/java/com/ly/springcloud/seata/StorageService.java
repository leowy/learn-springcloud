package com.ly.springcloud.seata;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

/**
 * @Auther: Leowy Zhuang
 * @Date: 2020/8/17 15:29
 * @Description:
 */
@Service
public class StorageService {

    @Autowired
    RestTemplate restTemplate;

    public String decrease(@RequestParam("productId") Long productId, @RequestParam("residue") int residue) {
        return  restTemplate.getForObject("http://seata-storage-service/storage/decrease?productId={1}&residue={2}", String.class, productId, residue);
    }

}
