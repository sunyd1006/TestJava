package com.syd.springboot.bean;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;


/**
 *  @PropertySource: 可以配置配置文件的路径，和@ConfigurationProperties有点类似
 *  @ImportResource：
 */
@Component
//@PropertySource(value = {"classpath:person.properties"})
@ConfigurationProperties(prefix = "person")
public class TestConfigurationProperties {

    private String name;

    //   full-name in application.yml, 松散配置的特性
    private String fullName;
    private Integer age;
    private Dog dog;

    public Dog getDog() {
        return dog;
    }

    public void setDog(Dog dog) {
        this.dog = dog;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "TestConfigurationProperties{" +
                "name='" + name + '\'' +
                ", fullName='" + fullName + '\'' +
                ", age=" + age +
                ", dog=" + dog +
                '}';
    }
}
