package com.ly.springcloud;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Auther: Leowy Zhuang
 * @Date: 2020/8/14 15:30
 * @Description:
 */
@EnableApolloConfig({"application","develop.demo"})
@Configuration
public class AppConfig {

    @Bean
    public ConfigBean configBean() {
        return new ConfigBean();
    }
}
