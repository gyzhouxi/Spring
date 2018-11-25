package cn.com.zhou;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Per implements InitializingBean,DisposableBean  {
	@Value("zhouxi")
	private String name;

	private int age;
	
	public Per() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Per(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public void start() {
		System.out.println("start");
	}
	public void destory() {
		System.out.println("destroy");
	}
	@Override
	public String toString() {
		return "Per [name=" + name + ", age=" + age + "]";
	}
	@Override
	public void destroy() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("destroy2");
	}
	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("start2");
	}
	
}
