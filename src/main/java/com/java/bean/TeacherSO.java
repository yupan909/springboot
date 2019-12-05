package com.java.bean;

import com.java.bean.base.Query;

import java.io.Serializable;

/**
 * 老师查询
 *
 * @author yupan@yijiupi.cn
 * @date 2019-12-05 10:50
 */
public class TeacherSO extends Query implements Serializable {

    private static final long serialVersionUID = 4206720557460996545L;

    /**
     * 教师姓名
     */
    private String name;

    /**
     * 教学科目（1：语文 2：数学 3：英语）
     */
    private Byte subject;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Byte getSubject() {
        return subject;
    }

    public void setSubject(Byte subject) {
        this.subject = subject;
    }
}
