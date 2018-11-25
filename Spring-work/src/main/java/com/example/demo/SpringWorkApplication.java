package com.example.demo;

import java.util.Arrays;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.controller.Hello;

import de.codecentric.boot.admin.config.EnableAdminServer;




@SpringBootApplication
@Configuration
@EnableAutoConfiguration
@EnableAdminServer

public class SpringWorkApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringWorkApplication.class, args);
	}
	
	private static final Logger logger = LoggerFactory.getLogger(Hello.class);

	@PostConstruct
	public String index() {

		logger.debug("Hello Debug Message");
		logger.info("Hello Info Message");
		logger.trace("Hello trace Message");
		logger.error("Hello error Message");
		logger.warn("Hello warn Message");
		return "Hello World";
	}
	
	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args ->{
			System.out.println("list:bean" + ctx.getBeanDefinitionCount());
			
			/*String[] beanNames = ctx.getBeanDefinitionNames();
			Arrays.sort(beanNames);
			for (String string : beanNames) {
				System.out.println(beanNames);
			}*/
		};
	}
}
