package com.java.controller;

import com.github.pagehelper.PageInfo;
import com.java.bean.Student;
import com.java.bean.base.BaseResult;
import com.java.bean.base.Query;
import com.java.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 学生管理
 *
 * @author yupan@yijiupi.cn
 * @date 2018/8/29 11:57
 */
@RequestMapping("/student")
@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    /**
     * 查询学生信息列表
     *
     * @param query
     * @return
     */
    @PostMapping("/listStudent")
    public BaseResult listStudent(Query query) {
        PageInfo<Student> list = studentService.listStudent(query);
        return new BaseResult<>(list);
    }

    /**
     * 查询学生信息
     *
     * @param id
     * @return
     */
    @GetMapping("/getStudent/{id}")
    public BaseResult getStudent(@PathVariable("id") Long id) {
        Student student = studentService.getStudent(id);
        return new BaseResult<>(student);
    }

    /**
     * 新增学生信息
     *
     * @param student
     * @return
     */
    @PostMapping("/saveStudent")
    public BaseResult saveStudent(Student student) {
        studentService.saveStudent(student);
        return BaseResult.successResult();
    }

    /**
     * 修改学生信息
     *
     * @param student
     * @return
     */
    @PostMapping("/updateStudent")
    public BaseResult updateStudent(Student student) {
        studentService.updateStudent(student);
        return BaseResult.successResult();
    }

    /**
     * 删除学生信息
     *
     * @param id
     * @return
     */
    @GetMapping("/removeStudent/{id}")
    public BaseResult removeStudent(@PathVariable("id") Long id) {
        studentService.removeStudent(id);
        return BaseResult.successResult();
    }

}
