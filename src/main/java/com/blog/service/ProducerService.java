package com.blog.service;

import com.blog.entity.vo.UserVo;

import javax.jms.Destination;

/**
 * @author shibo
 */
public interface ProducerService {
    // 点对点发送消息
    void sendMessage(Destination destination, final String message);

    // 调用方法并传递实体参数
    void doMethod(String method, Object parameter);

    // 消息发布
    void publish(final Destination topic, final String msg);

}
