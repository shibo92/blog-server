package com.blog.listener;

import com.blog.common.ObjectMessage;
import com.blog.service.ActiveMQService;
import org.apache.activemq.command.ActiveMQObjectMessage;
import org.springframework.beans.factory.annotation.Autowired;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import java.lang.reflect.Method;

public class TopicConsumerMsgListener1 implements MessageListener {

    @Autowired
    private ActiveMQService activeMQServiceImpl;

    /* @Autowired
    private ActiveMQService activeMQServiceImpl;*/

   @Override
    public void onMessage(Message message) {
        try {
            if (message instanceof TextMessage) {
                TextMessage textMsg = (TextMessage) message;
                System.out.println("消费者1 接收到文本消息：" + textMsg.getText());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
