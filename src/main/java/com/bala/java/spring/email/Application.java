package com.bala.java.spring.email;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@ComponentScan("com.bala.java")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
