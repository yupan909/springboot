package com.java.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.java.bean.Student;
import com.java.bean.base.Query;
import com.java.mapper.StudentMapper;
import com.java.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 学生管理接口实现类
 *
 * @author yupan@yijiupi.cn
 * @date 2018/8/29 11:58
 */
@CacheConfig(cacheNames = "student")
@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    /**
     * 查询学生信息列表
     *
     * @param query
     * @return
     */
    @Override
    public PageInfo<Student> listStudent(Query query) {
        List<Student> studentList = studentMapper.listStudent(query);
        PageInfo<Student> pageInfo = new PageInfo(studentList);
        return pageInfo;
    }

    /**
     * 查询学生信息
     *
     * @param id
     * @return
     */
    @Cacheable(key = "#id")
    @Override
    public Student getStudent(Long id) {
        return studentMapper.selectById(id);
    }

    /**
     * 新增学生信息
     *
     * @param student
     */
    @CachePut(key = "#result.id")
    @Override
    public Student saveStudent(Student student) {
        studentMapper.insert(student);
        return student;
    }

    /**
     * 修改学生信息
     *
     * @param student
     */
    @CachePut(key = "#student.id")
    @Override
    public Student updateStudent(Student student) {
        studentMapper.updateById(student);
        System.out.println(JSON.toJSONString(studentMapper.selectById(student.getId())));
        return student;
    }

    /**
     * 删除学生信息
     *
     * @param id
     */
    @CacheEvict(key = "#id")
    @Override
    public void removeStudent(Long id) {
        studentMapper.deleteById(id);
    }
}
