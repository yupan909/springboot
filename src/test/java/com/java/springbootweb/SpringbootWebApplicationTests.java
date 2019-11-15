package com.java.springbootweb;

import com.alibaba.fastjson.JSON;
import com.java.bean.Student;
import com.java.bean.base.Query;
import com.java.config.elasticsearch.ElasticSearchUtil;
import com.java.config.elasticsearch.Employee;
//import com.java.config.elasticsearch.EmployeeRepository;
import com.java.config.mail.MailService;
import com.java.config.rabbitmq.RabbitMqCustomer;
import com.java.config.rabbitmq.RabbitMqProvider;
import com.java.config.redis.RedisEnum;
import com.java.config.redis.RedisKeyGenerator;
import com.java.config.redis.RedisService;
import com.java.config.task.AsyncService;
import com.java.config.task.ScheduleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootWebApplicationTests {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private RedisService<Student> redisService;

    @Autowired
    private RabbitMqProvider rabbitMqProvider;

    @Autowired
    private ElasticSearchUtil elasticSearchUtil;

    @Autowired
    private AsyncService asyncService;

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private MailService mailService;

    @Test
    public void contextLoads() {
        System.out.println(dataSource.getClass());
        System.out.println(dataSource);
    }

    @Test
    public void redisTest() {
        Student student = new Student();
        student.setId(Long.valueOf("001"));
        student.setName("余攀");
        String key = RedisKeyGenerator.generator(RedisEnum.供应链, RedisEnum.库存, "student", student.getId().toString());
        redisService.set(key, student);
        System.out.println(JSON.toJSONString(redisService.get(key)));
    }

    @Test
    public void rabbitMqTest() {
        Student student = new Student();
        student.setId(Long.valueOf("001"));
        student.setName("余攀");
        rabbitMqProvider.send(student);
    }

    @Test
    public void elasticSearchTest() {
//        Employee emp = new Employee();
//        emp.setId("001");
//        emp.setName("james");
//        emp.setAge(34);
//        elasticSearchUtil.index("test", "emp", emp);
        String json = "{\n" +
                "    \"query\" : {\n" +
                "        \"match\" : {\n" +
                "            \"id\" : \"001\"\n" +
                "        }\n" +
                "    }\n" +
                "}";
        String result = elasticSearchUtil.search("test", "emp", json);
        System.out.println(result);
    }

    @Test
    public void asyncTest() {
        asyncService.test();
        System.out.println(Thread.currentThread() + "异步任务执行之后..");
    }

    @Test
    public void scheduleTest() {
        scheduleService.test();
    }

    @Test
    public void mailTest() throws Exception {
//        mailService.sendSimpleMail("506424124@qq.com", "开会通知", "今天下午3点开会！");
        List<File> files = new ArrayList<>();
        files.add(new File("E:/公司域.txt"));
        files.add(new File("E:/酒类一.jpg"));
        mailService.sendMail("506424124@qq.com", "开会通知", "今天下午4点开会！", files);
    }

}
