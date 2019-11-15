package com.java.config.elasticsearch;

import org.springframework.data.annotation.Id;

/**
 * @author yupan@yijiupi.cn
 * @date 2018/12/25 18:07
 */
public class Employee {

    private String id;

    private String name;

    private Integer age;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
