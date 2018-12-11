package com.qjin.platform.mq.activemq.noSping;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.stereotype.Service;

public class Producer {
	//ActiveMq 的默认用户名
    private static final String USERNAME = "admin";
    //ActiveMq 的默认登录密码
    private static final String PASSWORD = "admin";
    //ActiveMQ 的链接地址
    private static final String BROKEN_URL = "tcp://127.0.0.1:61616";
    
  //链接工厂
    ConnectionFactory connectionFactory;
    //链接对象
    Connection connection;
    //会话
    Session session;
    
    public void init(String queueName){
    	try {
    		connectionFactory = new ActiveMQConnectionFactory(USERNAME, PASSWORD, BROKEN_URL);
        	//从工厂中创建一个链接
            connection  = connectionFactory.createConnection();
            //连接打开
            connection.start();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
           sendMessage(queueName);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    }
    
    public void sendMessage(String disname){
    	//创建一个队列
    	try {
			Queue queue = session.createQueue(disname);
			//消息生产者
            MessageProducer messageProducer = session.createProducer(queue);
            for (int i = 0; i < 4; i++) {
            	//生产消息
            	 TextMessage msg = session.createTextMessage(Thread.currentThread().getName()+"productor:我现在正在生产东西！,count:"+i);
            	 System.out.println("--------->>>正在生产东西！,count:"+i);
            	 //发送消息
            	 messageProducer.send(msg);
            	 //提交
           /* 	 session.commit();
            	 connection.close();*/
            }
           
		} catch (JMSException e) {
			e.printStackTrace();
		}
    }
    
    public static void main(String[] args) {
    	Producer producer = new Producer();
    	producer.init("queue_1");
	}
}
