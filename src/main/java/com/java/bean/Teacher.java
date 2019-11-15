package com.java.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 教师
 *
 * @author yupan@yijiupi.cn
 * @date 2018/8/29 20:51
 */
public class Teacher implements Serializable {
    private static final long serialVersionUID = -2627562307604435425L;

    /**
     * id
     */
    private Long id;

    /**
     * 教师姓名
     */
    private String name;

    /**
     * 教学科目（1：语文 2：数学 3：英语）
     */
    private Byte subject;

    /**
     * 学生集合
     */
    private List<Student> students;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}
