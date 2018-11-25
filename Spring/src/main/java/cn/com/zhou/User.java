package cn.com.zhou;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class User {

	
	@Bean
	public Per getper() {
		Per per =new Per();
		per.setAge(5);
		return per;
	}
}
