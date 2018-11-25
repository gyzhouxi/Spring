package com.example.demo.controller;

import static org.mockito.Mockito.RETURNS_DEEP_STUBS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.support.ServletRequestHandledEvent;

import com.example.demo.bean.Animal;
import com.example.demo.bean.MessageHello;

@RestController
public class Hello implements CommandLineRunner {
	@Autowired
	Animal animal;
	@Autowired
	private MessageHello MessageHello;
	
	
	@RequestMapping("hello")
	public String sayhello() {
		return animal.toString();

	}
	
	

	@Bean  //监听hello页面
	public ApplicationListener<ApplicationEvent> helloListener() {
		final String HELLO_URL = "/hello";
		return (ApplicationEvent event) -> {
			if (event instanceof ServletRequestHandledEvent) {
				ServletRequestHandledEvent e = (ServletRequestHandledEvent) event;
				if (e.getRequestUrl().equals(HELLO_URL)) {
					System.out.println("OK");
					System.out.println(e.getRequestUrl());
					System.out.println(HELLO_URL);
				}
			}
		};
	}



	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		System.out.println(MessageHello.Message());
		
	}
}
