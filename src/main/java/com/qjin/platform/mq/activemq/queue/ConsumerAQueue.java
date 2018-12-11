package com.qjin.platform.mq.activemq.queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import com.qjin.platform.service.impl.EmailServiceImpl;

@Component
public class ConsumerAQueue {
	
	@Autowired
    private EmailServiceImpl  emailServiceImpl;
	
    // 使用JmsListener配置消费者监听的队列，其中text是接收到的消息
    @JmsListener(destination = "springboot.queue")
    public void receiveQueue(String meg){
        System.out.println("------------>>>MQ:::::"+this.getClass().getName()+" receive msg:"+meg);
        emailServiceImpl.sendEmail(meg);
    }
}
