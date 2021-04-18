package com.syd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @SpringBootApplication: 标志这是一个主程序，说明是Spring boot 应用
 */
@SpringBootApplication
public class HelloWorldMainApplication {
    public static void main(String[] args) {

        // Spring应用启动
        SpringApplication.run(HelloWorldMainApplication.class, args);
    }
}
