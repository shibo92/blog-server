package com.blog.service.impl;

import com.blog.entity.User;
import com.blog.service.ActiveMQService;
import org.springframework.stereotype.Service;

/**
 * @author shibo
 */
@Service
public class ActiveMQServiceImpl implements ActiveMQService {

    @Override
    public void sayHello(User user) {
        System.out.println("调用service方法成功...参数：" + user.getUsername());
    }
}
