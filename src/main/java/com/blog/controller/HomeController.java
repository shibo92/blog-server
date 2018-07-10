package com.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author shibo
 */
@Controller
@RequestMapping(value="/home")
public class HomeController {
    @RequestMapping(value = "")
    public ModelAndView index(ModelAndView mv){
        mv.setViewName("home");
        return mv;
    }
}
