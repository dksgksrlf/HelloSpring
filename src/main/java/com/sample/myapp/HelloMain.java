package com.sample.myapp;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

public class HelloMain {
	//생성자를 이용한 의존성 주입
	public static void main(String[] args) {
		AbstractApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		/*AbstractApplicationContext context = new GenericXmlApplicationContext("application-config.xml");*/
		System.out.println("---------------------------");
		HelloController helloController = context.getBean(HelloController.class);
		helloController.hello("홍길동");
		System.out.println("===========================");
		context.close();
		/*
		IHelloService helloService = new HelloService();
		HelloController controller = new HelloController(helloService);
		controller.hello("홍길동");*/
	}

}
