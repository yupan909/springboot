package com.java.service;

import com.github.pagehelper.PageInfo;
import com.java.bean.Student;
import com.java.bean.StudentSO;

/**
 * 学生管理接口类
 *
 * @author yupan@yijiupi.cn
 * @date 2018/8/29 11:58
 */
public interface StudentService {

    /**
     * 查询学生信息列表
     *
     * @return
     */
    PageInfo<Student> listStudent(StudentSO studentSO);

    /**
     * 查询学生信息
     *
     * @param id
     * @return
     */
    Student getStudent(Long id);

    /**
     * 新增学生信息
     *
     * @param student
     */
    Student saveStudent(Student student);

    /**
     * 修改学生信息
     *
     * @param student
     */
    Student updateStudent(Student student);

    /**
     * 删除学生信息
     *
     * @param id
     */
    void deleteStudent(Long id);
}
