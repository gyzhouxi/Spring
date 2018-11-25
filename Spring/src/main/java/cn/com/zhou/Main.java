package cn.com.zhou;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("Beans.xml");
		System.out.println(applicationContext.getBean("peo",Per.class).toString());
		//AnnotationConfigApplicationContext aContext = new AnnotationConfigApplicationContext(User.class);
		//System.out.println(aContext.getBean("getper",Per.class).toString());
		
		//aContext.scan("cn.com.zhou");
		//aContext.refresh();
		//aContext.registerShutdownHook();
		//System.out.println(aContext.getBean("per",Per.class).toString());
	}

}
