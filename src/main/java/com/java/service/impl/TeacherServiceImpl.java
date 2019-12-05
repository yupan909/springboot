package com.java.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.java.bean.Teacher;
import com.java.bean.TeacherSO;
import com.java.mapper.StudentMapper;
import com.java.mapper.TeacherMapper;
import com.java.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Autowired
    private StudentMapper studentMapper;

    /**
     * 查询教师信息列表
     *
     * @return
     */
    @Override
    public PageInfo<Teacher> listTeacher(TeacherSO teacherSO) {
        Page<Teacher> teacherList = teacherMapper.listTeacher(teacherSO);
        return teacherList.toPageInfo();
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
    public void deleteTeacher(Long id) {
        teacherMapper.deleteById(id);
    }
}
