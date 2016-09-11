package com.javacodegeeks.spring.jms;

import java.net.URI;
import java.net.URISyntaxException;

import org.apache.activemq.broker.BrokerFactory;
import org.apache.activemq.broker.BrokerService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringJmsMessageConverterExample {
	public static void main(String[] args) throws URISyntaxException, Exception {
		BrokerService broker = BrokerFactory.createBroker(new URI(
				"broker:(tcp://localhost:61616)"));
		broker.start();
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"applicationContext.xml");

		try {
			SpringJmsPersonProducer springJmsProducer = (SpringJmsPersonProducer) context
					.getBean("springJmsPersonProducer");
			springJmsProducer.sendMessage(new Person("Joe", 32));

			
		} finally {
			broker.stop();
			context.close();
		}
	}
}
