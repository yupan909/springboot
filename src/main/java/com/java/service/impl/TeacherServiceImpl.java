package com.java.service.impl;

import com.github.pagehelper.PageInfo;
import com.java.bean.Teacher;
import com.java.bean.base.Query;
import com.java.mapper.TeacherMapper;
import com.java.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 教师管理接口实现类
 *
 * @author yupan@yijiupi.cn
 * @date 2018/9/4 10:37
 */
@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherMapper teacherMapper;

    /**
     * 查询教师信息列表
     *
     * @param query
     * @return
     */
    @Override
    public PageInfo<Teacher> listTeacher(Query query) {
        List<Teacher> teacherList = teacherMapper.listTeacher(query);
        PageInfo<Teacher> pageInfo = new PageInfo<>(teacherList);
        return pageInfo;
    }

    /**
     * 查询教师信息
     *
     * @param id
     * @return
     */
    @Override
    public Teacher getTeacher(Long id) {
        return teacherMapper.selectById(id);
    }

    /**
     * 新增教师信息
     *
     * @param teacher
     */
    @Override
    public void saveTeacher(Teacher teacher) {
        teacherMapper.insert(teacher);
    }

    /**
     * 修改教师信息
     *
     * @param teacher
     */
    @Override
    public void updateTeacher(Teacher teacher) {
        teacherMapper.updateById(teacher);
    }

    /**
     * 删除教师信息
     *
     * @param id
     */
    @Override
    public void removeTeacher(Long id) {
        teacherMapper.deleteById(id);
    }
}
