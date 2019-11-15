package com.java.mapper;

import com.java.bean.base.Query;
import com.java.bean.Student;

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
     * @param query
     * @return
     */
    List<Student> listStudent(Query query);


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
