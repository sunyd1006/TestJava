package com.syd.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;

/**
 * @ImportResource: 用beans.xml配置Bean, 一般在主类声明
 */
//@ImportResource(locations = {"classpath:beans.xml"})
@SpringBootApplication
public class SpringBootHelloworldQuickApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootHelloworldQuickApplication.class, args);
	}

}
