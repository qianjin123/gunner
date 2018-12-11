package com.qjin.platform.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.qjin.platform.service.EmailService;

@Service
public class EmailServiceImpl implements EmailService {
	
	@Autowired
    private JavaMailSender mailSender;
	
	@Value("${spring.mail.username}")
	private String emailFrom;
	
	@Override
	public void sendEmail(String meg) {
	       SimpleMailMessage message = new SimpleMailMessage();
	        message.setFrom(emailFrom);
	        message.setTo("373049669@qq.com");
	        message.setSubject("主题：新用户提醒");
	        message.setText(meg );

	        mailSender.send(message);
	        System.out.println("--------------已发送邮件---------------");
	}

}
