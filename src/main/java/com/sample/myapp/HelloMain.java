package com.sample.myapp;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class HelloMain {
	//생성자를 이용한 의존성 주입
	public static void main(String[] args) {
		
/*		AbstractApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
*/		AbstractApplicationContext context = new GenericXmlApplicationContext("application-config.xml");
		System.out.println("---------------------------");
		HelloController helloController = context.getBean(HelloController.class);
		helloController.hello("홍길동");
		System.out.println("===========================");
		helloController.goodbye("감자바");
		context.close();
		/*
		IHelloService helloService = new HelloService();
		HelloController controller = new HelloController(helloService);
		controller.hello("홍길동");*/
	}

}
