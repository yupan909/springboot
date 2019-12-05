package com.java.mapper;

import com.github.pagehelper.Page;
import com.java.bean.Teacher;
import com.java.bean.TeacherSO;

/**
 * 教师管理Mapper
 *
 * @author yupan@yijiupi.cn
 * @date 2018/8/29 20:57
 */
public interface TeacherMapper {

    /**
     * 查询教师信息列表
     *
     * @return
     */
    Page<Teacher> listTeacher(TeacherSO teacherSO);

    /**
     * 查询教师信息
     *
     * @param id
     * @return
     */
    Teacher selectById(Long id);

    /**
     * 新增教师信息
     *
     * @param teacher
     */
    void insert(Teacher teacher);

    /**
     * 修改教师信息
     *
     * @param teacher
     */
    void updateById(Teacher teacher);

    /**
     * 删除教师信息
     *
     * @param id
     */
    void deleteById(Long id);

}
