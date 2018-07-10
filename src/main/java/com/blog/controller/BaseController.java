package com.blog.controller;

import com.blog.common.Constants;
import com.blog.entity.vo.UserVo;
import org.apache.log4j.Logger;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;

/**
 * @author shibo
 */
public class BaseController extends HttpServlet {
    protected Logger logger = Logger.getLogger(this.getClass());

    public HttpSession getHttpSession() {
        HttpSession session = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession();
        return session;
    }
    public UserVo getSessionUser() {
        UserVo user = (UserVo) ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession().getAttribute(Constants.SESSION_USER);
        return user;
    }
}
