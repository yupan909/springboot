package com.java.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * Druid数据源配置，同时开启Druid监控（http://localhost:8080/druid/index.html）
 *
 * @author yupan@yijiupi.cn
 * @date 2018/8/29 14:48
 */
@Configuration
public class DruidConfig {

    /**
     * 配置Druid数据源
     * 将配置文件的属性绑定到DruidDataSource中
     */
    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DataSource druid() {
        return new DruidDataSource();
    }

    /**
     * 开启Druid监控
     * 1. 配置一个后台管理的servlet
     *
     * @return 返回监控注册的servlet对象
     */
    @Bean
    public ServletRegistrationBean statViewServlet() {
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
        Map<String, String> initParam = new HashMap<>();
        // 添加控制台管理用户
        initParam.put("loginUsername", "root");
        initParam.put("loginPassword", "password");
        // 添加IP白名单
//        initParam.put("allow", "localhost");
        // 添加IP黑名单，当白名单和黑名单重复时，黑名单优先级更高
//        initParam.put("deny", "127.0.0.1");
        // 是否能够重置数据
        initParam.put("resetEnable", "false");
        servletRegistrationBean.setInitParameters(initParam);
        return servletRegistrationBean;
    }

    /**
     * 开启Druid监控
     * 2. 配置一个web监控的filter
     *
     * @return 返回过滤器配置对象
     */
    @Bean
    public FilterRegistrationBean webStatFilter() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new WebStatFilter());
        // 添加过滤规则
        filterRegistrationBean.addUrlPatterns("/*");
        // 忽略过滤格式
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*,");
        return filterRegistrationBean;
    }
}
