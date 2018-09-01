package com.blog.service.impl;

import com.blog.aop.SystemLog;
import com.blog.entity.User;
import com.blog.entity.vo.UserVo;
import com.blog.mapper.UserMapper;
import com.blog.service.UserService;
import com.blog.utils.DateUtil;
import com.blog.utils.MD5;
import com.blog.utils.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author shibo
 */
@Service
public class UserServiceImpl implements UserService {
  /*  @Resource(name = "daoSupport")
    private DaoSupport daoSupport;*/
    @Autowired
    private UserMapper userMapper;

    @Override
    public void save(User user) throws Exception {
        user.setPassword(MD5.getMd5Code(user.getPassword()));
        user.setId(UUID.get32Code());
        user.setRegTime(DateUtil.getDateTime());
        user.setIsDel(0);
        userMapper.save(user);
    }

    @Override
    public UserVo getUserVoById(String userId) throws Exception {
        return userMapper.getUserVoById(userId);
    }

    @SystemLog(description = "service")
    @Override
    public User getUserTest(User u) throws Exception {
        // return (User) daoSupport.findForObject("UserMapper.getUserTest", u);
        return null;
    }
}
