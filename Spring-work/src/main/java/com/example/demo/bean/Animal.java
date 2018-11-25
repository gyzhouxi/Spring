package com.example.demo.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Animal {
	@Autowired
	private  Cat cat;
	@Autowired
	private Dog dog;
	
	public Cat getCat() {
		return cat;
	}

	public void setCat(Cat cat) {
		this.cat = cat;
	}

	public Dog getDog() {
		return dog;
	}

	public void setDog(Dog dog) {
		this.dog = dog;
	}

	public Animal() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Animal(Cat cat, Dog dog) {
		super();
		this.cat = cat;
		this.dog = dog;
	}

	@Override
	public String toString() {
		return "Animal [cat=" + cat + ", dog=" + dog + "]";
	}
	

}
