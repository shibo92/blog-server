package com.blog.entity.vo;

import com.blog.entity.User;
import com.blog.entity.UserInfo;

/**
 * @author shibo
 */
public class UserVo {
    User user;
    UserInfo userInfo;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }
}
