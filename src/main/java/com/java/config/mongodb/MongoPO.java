package com.java.config.mongodb;

import org.bson.types.ObjectId;

import java.io.Serializable;
import java.util.Date;

/**
 * mongo对象
 *
 * @author yupan@yijiupi.cn
 * @date 2019-11-21 17:51
 */
public class MongoPO implements Serializable {

    private static final long serialVersionUID = 2777012064044558429L;

    /**
     * 主键
     */
    private String id = ObjectId.get().toString();

    /**
     * 名称
     */
    private String name;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 创建时间
     */
    private Date createTime;

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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
