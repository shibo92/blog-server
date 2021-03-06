package com.blog.service.impl;
import com.blog.common.ObjectMessage;
import com.blog.service.ProducerService;
import org.apache.activemq.command.ActiveMQObjectMessage;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.JmsException;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import javax.jms.*;

/**
 * @author shibo
 */
@Service
public class ProducerServiceImpl implements ProducerService {
    @Autowired
    private JmsTemplate jmsTemplate;
    @Autowired
    private ActiveMQQueue pushMsgDestination;

    @Override
    public void sendMessage(Destination destination, String message) {
        jmsTemplate.send(destination, new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(message);
            }
        });
    }

    @Override
    public void doMethod(String method, Object parameter) {
        try {
            ActiveMQObjectMessage message = new ActiveMQObjectMessage();
            ObjectMessage object = new ObjectMessage(method, parameter);
            message.setObject(object);
            jmsTemplate.convertAndSend(pushMsgDestination, message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void publish(final Destination topic, final String msg) {
        jmsTemplate.send(topic, new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                System.out.println("topic name 是" + topic.toString()
                        + "，发布消息内容为:\t" + msg);
                return session.createTextMessage(msg);
            }
        });
    }
}
