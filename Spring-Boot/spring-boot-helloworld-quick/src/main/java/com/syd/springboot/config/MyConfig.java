package com.syd.springboot.config;

import com.syd.springboot.service.HelloService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @ImportResource(locations = {"classpath:beans.xml"}): 用beans.xml配置Bean, 一般在主类声明
 * @PropertySource(value = {"classpath:person.properties"})，一般在bean包里面声明
 * @ConfigurationProperties(prefix = "person"), 读取的时application.properties/yml , 一般在bean里面声明
 * @Configuration: 指明当前类是一个配置类，就是替代之前的Spring配置文件，一般在config.MyConfig里面声明
 */
@Configuration
public class MyConfig {

    // 将方法的返回值添加到容器中，容器中这个组件默认的id就是方法名
//    @Bean
//    public HelloService helloService(){
//        System.out.println("配置类@Bean给容器中添加组件");
//        return new HelloService();
//    }
}
