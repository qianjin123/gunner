package com.qjin.platform.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qjin.platform.dao.UserMapper;
import com.qjin.platform.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserMapper  userMapper;

	@Override
	public List<Map<String, Object>> getList() {
		
		return userMapper.queryUserList();
	}
	
	
}
