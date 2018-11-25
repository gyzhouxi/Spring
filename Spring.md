# 依赖注入

######  通过配置xml文件实现bean的属性注入

​	1)id用来选择需要注入的bean

​	2)class 用来指定类

​	3)property 通过属性名字注入 ，需要有get set 方法

​	4)construcator 通过构造方式注入 ，需要有带有参数的构造方法

~~~ xml
<bean id="peo" class="cn.com.zhou.Per">
		<property name="name" value="zhou"></property>
		<property name="age" value="22"></property>
	</bean>
	<bean id="peo2" class="cn.com.zhou.Per">
		<constructor-arg type="String" value="guang"></constructor-arg>
		<constructor-arg type="int" value="22"></constructor-arg>
	</bean>
~~~

###### 通过注解的方式配置



扫描cn.com.zhou 包路径下的所有bean文件

~~~ xml
<context:component-scan base-package="cn.com.zhou"></context:component-scan>
~~~

测试方法

~~~ java
AnnotationConfigApplicationContext aContext = new 	  AnnotationConfigApplicationContext();
		aContext.scan("cn.com.zhou");
		aContext.refresh();
		aContext.registerShutdownHook();
		System.out.println(aContext.getBean("per",Per.class).toString());
~~~

###### 基于javal类的配置

​	需要注解

@configuration

@bean

~~~ java

@Configuration
public class User {	
	@Bean
	public Per getper() {
		Per per =new Per();
		per.setAge(5);
		return per;
	}
}

~~~



测试方法

~~~ java 
AnnotationConfigApplicationContext aContext = new AnnotationConfigApplicationContext(User.class);
			System.out.println(aContext.getBean("getper",Per.class).toString());
		
~~~



# bean的 init-method 和destory 方法

###### bean标签设置



~~~ xml
<bean id="peo" class="cn.com.zhou.Per" init-method="start" destroy-method="destroy">
		<property name="name" value="zhou"></property>
		<property name="age" value="22"></property>
	</bean>
~~~

per类添加方法 start() destory()

~~~ java
public void start() {
		System.out.println("start");
	}
	public void destory() {
		System.out.println("destroy");
	}
~~~

###### 方法二 实现 InitializingBean,DisposableBean 

~~~ java
public class Per implements InitializingBean,DisposableBean 
@Override
	public void destroy() throws Exception {
		// TODO Auto-generated method stub
		
	}
	@Ove0rride
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		
	}
~~~





# 使用.properties 或 .yml 文件 配置类属性值

propertise文件编写

~~~ propertise
cat.name=cat
dog.name=dogs
~~~

类文件的编写

需要注解 @component

​		@configurationPropertise(prefix="xxx")   xxx 代表propertise 路径 

​		类中的属性需要与propertise对应

###### @PropertySource(value="test.properties")

读取指定propertise下的内容

# 动态配置内部及外部配置

需要在类上添加注解 @Profile({"xxx"})  加载xxx 下的配置

加载“goodbye”下的内容

~~~ java

@Component
@Profile({"GoodBye"})
public class GoodBye implements MessageHello {
	@Value("${name:bye}")
	private String name;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	

	@Override
	public String toString() {
		return "GoodBye [name=" + name + "]";
	}

	@Override
	public String Message() {
		// TODO Auto-generated method stub
		return "Good"+this.name;
	}

}

~~~

propertise文件配置

~~~ propertise
name: zhou
spring:
  profiles:
    active: 
---
spring:
  profiles: GoodBye
name: GoodBye  


~~~



# 相应页面的请求及数据返回

需要注解 @Restcontroller 

@requestMapping("xxx")  相应xxx页面的请求

~~~ java

@RestController
public class Application {
	@Value("${name}")
	private String name;
	@RequestMapping("hello")
	public String hello() {
		return name;	
	}
	
~~~





# 监听器

~~~ java
监听hello页面
@Bean
public ApplicationListener<ApplicationEvent> helloListener(){
	final String HELLO_URL="/hello";
	return (ApplicationEvent event)->{
		if(event instanceof ServletRequestHandledEvent) {
			ServletRequestHandledEvent e=(ServletRequestHandledEvent) event;
			if(e.getRequestUrl().equals(HELLO_URL)) {
				System.out.println("OK");
				System.out.println(e.getRequestUrl());
				System.out.println(HELLO_URL);
			}
		}
	};
 
}
~~~

# @Override 注解 

优先加载 @Override() 后参数小的 bean



# 日志

### Logback-日志

Spring Boot包含许多Logback扩展，可以帮助进行高级配置。您可以在`logback-spring.xml`配置文件中使用这些扩展名。

~~~ xml
<?xml version="1.0" encoding="UTF-8"?>
<configuration>
	<include resource = "org/springframework/boot/logging/logback/base.xml"></include>
	<logger name = "com.example" level = "DEBUG"></logger>
	<springProfile name = "staging">
		<logger name = "com.example" level = "TRACE"></logger>
	</springProfile>
</configuration>
~~~



### #log4j-日志

log4j规定了默认的几个级别：trace<debug<info<warn<error

~~~ java
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
~~~

###### propertise 文件设置

root: info 控制日志级别，只显示info及以上的信息

~~~ xml
logging:
  level:
    root: info
    org.springframework.web: info
  file: logging-spring.lo
  
~~~



# 监控

pom.xml

~~~ java
	<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-</artifactId>
		</dependency>
        
~~~

peopertise文件

~~~ propertise
management:
  security:
    enabled: false
  port: 8081
  context-path: /aaa
  
  
info:
  app:
    name: zhougyx
    version: 1.0.0
~~~



# Spring boot Admin监控

#### admin-server：8080

1.pom.xml

~~~ xml
<dependency>
			<groupId>de.codecentric</groupId>
			<artifactId>spring-boot-admin-server</artifactId>
			<version>1.5.6</version>
		</dependency>
		<!--admin server的展示-->
		<dependency>
			<groupId>de.codecentric</groupId>
			<artifactId>spring-boot-admin-server-ui</artifactId>
			<version>1.5.6</version>
		</dependency>
~~~

2.启动类上加注解

~~~ java
@Configuration
@EnableAutoConfiguration
@EnableAdminServe
~~~

http://localhost:8080 访问

### admin-client：8082

1.pom.xml

~~~ xml
<dependency>
   <groupId>de.codecentric</groupId>
   <artifactId>spring-boot-admin-starter-client</artifactId>
   <version>1.5.6</version>
</dependency>
~~~

2.application.properties

~~~ propertise
spring.boot.admin.url=http://localhost:8080--  
management.security.enabled=false
~~~


