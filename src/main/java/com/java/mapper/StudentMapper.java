package com.java.mapper;

import com.github.pagehelper.Page;
import com.java.bean.Student;
import com.java.bean.StudentSO;
import com.java.bean.base.Query;

import java.util.List;

/**
 * 学生管理Mapper
 *
 * @author yupan@yijiupi.cn
 * @date 2018/8/29 20:57
 */
public interface StudentMapper {

    /**
     * 查询学生信息列表
     *
     * @return
     */
    Page<Student> listStudent(StudentSO studentSO);

    /**
     * 根据教师id查询学生信息列表
     * @param teacherId
     * @return
     */
    List<Student> listStudentByTeacherId(Long teacherId);

    /**
     * 查询学生信息
     *
     * @param id
     * @return
     */
    Student selectById(Long id);

    /**
     * 新增学生信息
     *
     * @param student
     */
    void insert(Student student);

    /**
     * 修改学生信息
     *
     * @param student
     */
    void updateById(Student student);

    /**
     * 删除学生信息
     *
     * @param id
     */
    void deleteById(Long id);

}
