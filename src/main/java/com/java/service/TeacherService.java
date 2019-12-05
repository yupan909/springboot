package com.java.service;

import com.github.pagehelper.PageInfo;
import com.java.bean.Teacher;
import com.java.bean.TeacherSO;

/**
 * 教师
 *
 * @author yupan@yijiupi.cn
 * @date 2018/8/29 20:55
 */
public interface TeacherService {

    /**
     * 查询教师信息列表
     *
     * @return
     */
    PageInfo<Teacher> listTeacher(TeacherSO teacherSO);

    /**
     * 查询教师信息
     *
     * @param id
     * @return
     */
    Teacher getTeacher(Long id);

    /**
     * 新增教师信息
     *
     * @param teacher
     */
    void saveTeacher(Teacher teacher);

    /**
     * 修改教师信息
     *
     * @param teacher
     */
    void updateTeacher(Teacher teacher);

    /**
     * 删除教师信息
     *
     * @param id
     */
    void deleteTeacher(Long id);
}
