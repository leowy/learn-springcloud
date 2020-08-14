package com.ly.springcloud;

import org.springframework.beans.factory.annotation.Value;

import java.io.Serializable;

/**
 * @Auther: Leowy Zhuang
 * @Date: 2020/8/14 15:22
 * @Description:
 */
public class ConfigBean implements Serializable {

    private static final long serialVersionUID = -1353850738280280234L;

    @Value("${name: name 没有值}")
    private String name;
    @Value("${company: company 没有值}")
    private String company;
    @Value("${url: url 没有值}")
    private String url;
    @Value("${test: test没有值}")
    private String test;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }

    @Override
    public String toString() {
        return "ConfigBean{" +
                "name='" + name + '\'' +
                ", company='" + company + '\'' +
                ", url='" + url + '\'' +
                ", test='" + test + '\'' +
                '}';
    }
}
