package com.java.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.java.bean.Teacher;
import com.java.bean.TeacherSO;
import com.java.bean.base.BaseResult;
import com.java.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 教师管理
 *
 * @author yupan@yijiupi.cn
 * @date 2018/8/29 11:57
 */
@RequestMapping("/teacher")
@RestController
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    /**
     * 查询教师信息列表
     *
     * @return
     */
    @PostMapping("/listTeacher")
    public BaseResult listTeacher(@RequestBody TeacherSO teacherSO) {
        PageInfo<Teacher> pageInfo = teacherService.listTeacher(teacherSO);
        return new BaseResult<>(pageInfo);
    }

    /**
     * 查询教师信息
     *
     * @param id
     * @return
     */
    @GetMapping("/getTeacher/{id}")
    public BaseResult getTeacher(@PathVariable("id") Long id) {
        Teacher teacher = teacherService.getTeacher(id);
        return new BaseResult<>(teacher);
    }

    /**
     * 新增教师信息
     *
     * @param teacher
     * @return
     */
    @PostMapping("/saveTeacher")
    public BaseResult saveTeacher(@RequestBody Teacher teacher) {
        System.out.println(JSON.toJSONString(teacher));
        teacherService.saveTeacher(teacher);
        return BaseResult.successResult();
    }

    /**
     * 修改教师信息
     *
     * @param teacher
     * @return
     */
    @PostMapping("/updateTeacher")
    public BaseResult updateTeacher(@RequestBody Teacher teacher) {
        System.out.println(JSON.toJSONString(teacher));
        teacherService.updateTeacher(teacher);
        return BaseResult.successResult();
    }

    /**
     * 删除教师信息
     *
     * @param id
     * @return
     */
    @GetMapping("/deleteTeacher/{id}")
    public BaseResult deleteTeacher(@PathVariable("id") Long id) {
        teacherService.deleteTeacher(id);
        return BaseResult.successResult();
    }

}
