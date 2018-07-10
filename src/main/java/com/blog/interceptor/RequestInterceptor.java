package com.blog.interceptor;

import com.blog.common.Constants;
import com.blog.entity.User;
import com.blog.entity.UserInfo;
import com.blog.entity.vo.UserVo;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author shibo
 */
public class RequestInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //todo 登陆做好之后 remove
        User user = new User();
        user.setId("dfbdac4b29b24d0cad99f57214ab5f64");
        UserInfo userInfo = new UserInfo();
        userInfo.setId("001db2a271ab410d8f544f371ab188b1");
        userInfo.setName("wall-e");
        userInfo.setPhone("110");
        userInfo.setEmail("shibo92@qq.com");
        UserVo userVo = new UserVo();
        userVo.setUser(user);
        userVo.setUserInfo(userInfo);
        request.getSession().setAttribute(Constants.SESSION_USER,userVo);
        return true;
    }
}