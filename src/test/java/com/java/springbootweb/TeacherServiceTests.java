package com.java.springbootweb;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.java.bean.Teacher;
import com.java.bean.TeacherSO;
import com.java.bean.base.Query;
import com.java.service.TeacherService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 教师测试类
 *
 * @author yupan@yijiupi.cn
 * @date 2019-12-04 17:45
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TeacherServiceTests {

    @Autowired
    private TeacherService teacherService;

    @Test
    public void listTeacher() {
        TeacherSO teacherSO = new TeacherSO();
        teacherSO.setPageNum(1);
        teacherSO.setPageSize(5);
        teacherSO.setSubject(Byte.valueOf("1"));
        PageInfo<Teacher> pageInfo = teacherService.listTeacher(teacherSO);
        System.out.println("======" + JSON.toJSONString(pageInfo));
    }

    @Test
    public void saveTeacher() {
        Teacher teacher = new Teacher();
        teacher.setId(Long.valueOf("3"));
        teacher.setName("何老师");
        teacher.setSubject(Byte.valueOf("2"));
        teacherService.saveTeacher(teacher);
    }
}
