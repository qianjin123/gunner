package com.qjin.platform.service;

import java.util.List;
import java.util.Map;

public interface UserService {
	public List<Map<String,Object>> getList();
	
	public void insertUser(Map<String, Object> map);
	
}
