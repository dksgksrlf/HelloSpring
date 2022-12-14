package com.sample.myapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class HelloController {
	@Autowired
	IHelloService helloService;
	/*public HelloController(IHelloService helloService) {
		this.helloService=helloService;
	}*/
	/*public void setHelloService(IHelloService helloService) {
		this.helloService=helloService;
	}*/
	public void hello(String name) {
		System.out.println("HelloController : "+helloService.sayHello(name));
	}
	
}
