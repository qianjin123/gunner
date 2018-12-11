package com.qjin.platform.mq.activemq.noSping;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class Consumer {
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
            getMessage(queueName);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    }
    
    public void getMessage(String disname){
    	//创建一个队列
    	try {
			Queue queue = session.createQueue(disname);
			//消息消费者
			MessageConsumer consumer = session.createConsumer(queue);
			consumer.setMessageListener(new MessageListener() {
				
				@Override
				public void onMessage(Message message) {
					if(message instanceof TextMessage){
	                    TextMessage textMessage = (TextMessage)message;
	                    try {
	                        System.out.println("------>consumer::"+textMessage.getText());
	                    } catch (JMSException e) {
	                        e.printStackTrace();
	                    }
	                }
				}
			});
			
			/*TextMessage msg = (TextMessage) consumer.receive();
			int count = 0;
				
				if(msg!=null) {
	                 msg.acknowledge();
	                 System.out.println(Thread.currentThread().getName()+": Consumer:我正在消费Msg"+msg.getText()+"--->");
	                 count ++;
				 }else {
	             }*/
			
			
           
		} catch (JMSException e) {
			e.printStackTrace();
		}
    }
    
    public static void main(String[] args) {
    	Consumer consumer = new Consumer();
    	consumer.init("queue_1");
	}
}
