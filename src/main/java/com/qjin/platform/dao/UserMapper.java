package com.qjin.platform.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

//@Component
@Mapper
public interface UserMapper {
	
	List<Map<String,Object>> queryUserList();
}
