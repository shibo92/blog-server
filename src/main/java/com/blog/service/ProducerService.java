package com.blog.service;

import com.blog.entity.vo.UserVo;

import javax.jms.Destination;

/**
 * @author shibo
 */
public interface ProducerService {
    void sendMessage(Destination destination, final String message);
}
