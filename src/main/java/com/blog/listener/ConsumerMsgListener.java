package com.blog.listener;

import com.blog.common.ObjectMessage;
import com.blog.service.ActiveMQService;
import com.blog.service.BlogService;
import com.blog.service.impl.BlogServiceImpl;
import org.apache.activemq.command.ActiveMQMessage;
import org.apache.activemq.command.ActiveMQObjectMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Reference;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import java.lang.reflect.Method;

public class ConsumerMsgListener implements MessageListener {

    @Autowired
    private ActiveMQService activeMQServiceImpl;

    @Override
    public void onMessage(Message message) {
        try {
            // 如果是自定义对象消息
            if (message instanceof ActiveMQObjectMessage) {
                ActiveMQObjectMessage objectMessage = (ActiveMQObjectMessage)message;
                ObjectMessage object = (ObjectMessage) objectMessage.getObject();
                // 利用反射调用service中的方法
                Class<?> clazz = activeMQServiceImpl.getClass();
                Method method = clazz.getMethod(object.getMethod(), object.getParameter().getClass());
                method.invoke(activeMQServiceImpl, object.getParameter());
            }
            if (message instanceof TextMessage) {
                TextMessage textMsg = (TextMessage) message;
                System.out.println("接收到文本消息：" + textMsg.getText());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
