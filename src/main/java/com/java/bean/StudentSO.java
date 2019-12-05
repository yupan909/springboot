package com.java.bean;

import com.java.bean.base.Query;

import java.io.Serializable;

/**
 * 学生查询
 *
 * @author yupan@yijiupi.cn
 * @date 2019-12-05 10:55
 */
public class StudentSO extends Query implements Serializable {

    private static final long serialVersionUID = 985721218417390852L;

    /**
     * 学生姓名
     */
    private String name;

    /**
     * 教师Id
     */
    private Long teacherId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }
}
