package com.java.mapper;

import com.java.bean.Teacher;
import com.java.bean.base.Query;

import java.util.List;

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
     * @param query
     * @return
     */
    List<Teacher> listTeacher(Query query);

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
