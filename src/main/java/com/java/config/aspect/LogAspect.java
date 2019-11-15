package com.java.config.aspect;

import com.alibaba.fastjson.JSON;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * 使用aop统一处理web请求日志
 *
 * @author Administrator
 */
@Component
@Aspect //申明是个切面
public class LogAspect {

    private final Logger log = LoggerFactory.getLogger(LogAspect.class);

    //申明一个切点 里面是 execution表达式
    @Pointcut("execution(public * com.java.controller.*.*(..))")
    private void logAspect() {
    }

    ///请求前打印内容
    @Before(value = "logAspect()")
    public void before(JoinPoint joinPoint) {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        //打印请求内容
        log.info("--------------request start--------------");
        log.info("url={}", request.getRequestURL().toString()); //请求地址
        log.info("method={}", request.getMethod());    //请求方式
        log.info("ip={}", request.getRemoteAddr());    //请求ip
        log.info("class_method={}", joinPoint.getSignature()); //请求类方法
        log.info("args={}", Arrays.toString(joinPoint.getArgs())); //请求类方法参数
        log.info("--------------request end   --------------");
    }

    @AfterReturning(returning = "obj", pointcut = "logAspect()")
    public void afterReturning(Object obj) {
        log.info("--------------response start----------------");
        log.info("response={}", JSON.toJSONString(obj));
        log.info("--------------response end  ----------------");
    }
}
