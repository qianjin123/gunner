package com.qjin.platform.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.qjin.platform.service.UserService;



@RestController
@RequestMapping("/user")
public class DemoController {
	
	@Autowired
	private UserService userService;
	
    @RequestMapping("/list")
    public List<Map<String,Object>> heloWord() {
        return userService.getList();
    }
}
