package com.blog.controller;

import com.alibaba.fastjson.JSON;
import com.blog.entity.User;
import com.blog.service.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.jms.Destination;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("jms")
public class JmsTestController {

    @Autowired
    private ProducerService producerService;
    @Autowired
    @Qualifier("queueDestination")
    private Destination destination;

    @RequestMapping("test")
    @ResponseBody
    public String testSend() throws Exception {

        //系统业务需要， 需要更新用户表中信息，根据id更新name
        List<User> list = new LinkedList<User>();

        User user = new User();
        user.setId("1");
        user.setUsername("username1");
        list.add(user);

        User user2 = new User();
        user2.setId("2222");
        user2.setUsername("姓名2222");
        list.add(user2);

        Map<String, Object> mapEntity = new HashMap<String, Object>();
        mapEntity.put("user", list);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("update", mapEntity);
        // System.out.println("发送方发送内容为：" + JSON.object2String(map));
        String sendMsgContent = JSON.toJSONString(map);
        System.out.println("发送方发送内容为：" + sendMsgContent);
        //发送更新数据请求
        // producerService.sendMessage(destination, JsonUtil.object2String(map));
        producerService.sendMessage(destination, sendMsgContent);

        return "jms exute complete";
    }
}

