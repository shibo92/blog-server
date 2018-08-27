package com.blog.service.impl;

import com.blog.aop.MyLog;
import com.blog.entity.User;
import com.blog.entity.vo.UserVo;
import com.blog.mapper.UserMapper;
import com.blog.service.ProducerService;
import com.blog.service.UserService;
import com.blog.utils.DateUtil;
import com.blog.utils.MD5;
import com.blog.utils.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

/**
 * @author shibo
 */
@Service
public class ProducerServiceImpl implements ProducerService {
    @Autowired
    private JmsTemplate jmsTemplate;

    @Override
    public void sendMessage(Destination destination, String message) {
        jmsTemplate.send(destination, new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(message);
            }
        });
    }
}
