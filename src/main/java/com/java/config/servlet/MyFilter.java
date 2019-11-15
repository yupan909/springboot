package com.java.config.servlet;

import javax.servlet.*;
import java.io.IOException;

/**
 * 配置Filter过滤器
 *
 * @author yupan@yijiupi.cn
 * @date 2018/8/22 10:21
 */
public class MyFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("myFilter...");
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
