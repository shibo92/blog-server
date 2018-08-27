package com.blog.listener;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class ConsumerMsgListener implements MessageListener {
    public void onMessage(Message message) {
        TextMessage textMsg = (TextMessage) message;
        try {
            System.out.println("接收者受到消息：" + textMsg.getText());
            System.out.println("开始进行解析并调用service执行....");

        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
