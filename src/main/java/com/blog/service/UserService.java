package com.blog.service;

import com.blog.entity.User;
import com.blog.entity.vo.UserVo;

/**
 * @author shibo
 */
public interface UserService {
    public void save(User user) throws Exception;
    public UserVo getUserVoById(String userId) throws Exception;
    public User getUserTest(User user) throws Exception;
}
