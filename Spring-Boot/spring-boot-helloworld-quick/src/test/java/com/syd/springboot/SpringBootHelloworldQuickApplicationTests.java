package com.syd.springboot;

import com.syd.springboot.bean.TestConfigurationProperties;
import com.syd.springboot.service.HelloService;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.ResponseStatus;

//@RunWith(SpringRunner.class)
@SpringBootTest
class SpringBootHelloworldQuickApplicationTests {

	@Autowired
	TestConfigurationProperties testConfigurationProperties;

	@Autowired
	// Spring的ioc容器
	ApplicationContext ioc;

	@Test
	public void testHelloService(){
		boolean b = ioc.containsBean("helloService");
		System.out.println(ioc.getBean("helloService"));
		System.out.println(b);
	}

	@Test
	void contextLoads() {
		System.out.println(testConfigurationProperties.toString());
	}
}
