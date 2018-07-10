package com.blog.controller;

import com.blog.aop.MyLog;
import com.blog.common.JsonResult;
import com.blog.entity.User;
import com.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author shibo
 */
@Controller
@RequestMapping(value = "/user")
public class UserController extends BaseController {

    @Autowired
    private UserService userServiceImpl;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    @MyLog
    public JsonResult get(@PathVariable("id") String id) {
        JsonResult jsonResult = new JsonResult();
        System.out.println("方法中");
        jsonResult.setCode("01");
        return jsonResult;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public void save(User user) {
        try {
            userServiceImpl.save(user);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

}
