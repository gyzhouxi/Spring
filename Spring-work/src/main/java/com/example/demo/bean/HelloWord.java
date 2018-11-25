package com.example.demo.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile({"hello","default","dev"})
public class HelloWord implements MessageHello {
	@Value("${name:name}")
	private String name;
	
	@Override
	public String toString() {
		return "HelloWord [name=" + name + "]";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String Message() {
		// TODO Auto-generated method stub
		return "Hello"+this.name;
	}

}
