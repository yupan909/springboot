package com.java.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

/**
 * 首页
 *
 * @author yupan@yijiupi.cn
 * @date 2018/8/22 17:18
 */
@RestController
public class IndexController {

    @RequestMapping(value = "/")
    public ModelAndView index() {
        System.out.println("index.html...");
        return new ModelAndView(new RedirectView("/index.html"));
    }
}
