package com.java.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 学生
 *
 * @author yupan@yijiupi.cn
 * @date 2018/8/29 20:49
 */
public class Student implements Serializable {

    private static final long serialVersionUID = -4712189081470501740L;

    /**
     * id
     */
    private Long id;

    /**
     * 学生姓名
     */
    private String name;

    /**
     * 出生日期
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd") // 将Date类型转化为String类型返给前端
    @DateTimeFormat(pattern = "yyyy-MM-dd")  // 将前端的String类型转化为Date类型
    private Date birthdate;

    /**
     * 教师Id
     */
    private Long teacherId;

    /**
     * 教师
     */
    private Teacher teacher;

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

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

}
