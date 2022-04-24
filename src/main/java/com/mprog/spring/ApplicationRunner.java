package com.mprog.spring;

import com.mprog.spring.config.ApplicationConfiguration;
import com.mprog.spring.database.pool.ConnectionPool;
import com.mprog.spring.database.repository.CompanyRepository;
import com.mprog.spring.database.repository.CrudRepository;
import com.mprog.spring.service.CompanyService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

@SpringBootApplication
public class ApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationRunner.class, args);
    }
}

