package com.qjin.platform.controller;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qjin.platform.service.UserService;



@RestController
@RequestMapping("/user")
public class DemoController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
    private JavaMailSender mailSender;
	
    @GetMapping("/list")
    public List<Map<String,Object>> list() {
    	
        return userService.getList();
    }
    
    @PostMapping
    public void  user(HttpServletRequest request) {
	    	Map<String,Object> map = new HashMap<>(); 
			//得到枚举类型的参数名称，参数名称若有重复的只能得到第一个 
			Enumeration enums = request.getParameterNames(); 
		    while (enums.hasMoreElements()) 
		    { 
			    String paramName = (String) enums.nextElement(); 
			    String paramValue = request.getParameter(paramName); 
			    //形成键值对应的map 
			    map.put(paramName, paramValue); 
		    }
	        userService.insertUser(map);
	        //发邮件
	       /* SimpleMailMessage message = new SimpleMailMessage();
	        message.setFrom("583529525@qq.com");
	        message.setTo("373049669@qq.com");
	        message.setSubject("主题：java mail 测试");
	        message.setText("新增了用户:" +map.get("user_name").toString() );

	        mailSender.send(message);
	        System.out.println("--------------已发送邮件---------------");*/
    }
}
