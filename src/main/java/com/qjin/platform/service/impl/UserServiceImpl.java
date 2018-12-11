package com.qjin.platform.service.impl;

import java.util.List;
import java.util.Map;

import javax.jms.Destination;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qjin.platform.dao.UserMapper;
import com.qjin.platform.mq.activemq.queue.ProducerQueue;
import com.qjin.platform.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserMapper  userMapper;
	
	@Autowired
    private ProducerQueue producerQueue;

	@Override
	public List<Map<String, Object>> getList() {
		
		return userMapper.queryUserList();
	}

	@Override
	public void insertUser(Map<String, Object> map) {
		userMapper.insertUser(map);
		//向mq发消息
		 Destination destination = new ActiveMQQueue("springboot.queue");
		     producerQueue.sendMessage(destination,  "hi, new user:"+map.get("user_name"));
	}
	
	
}
