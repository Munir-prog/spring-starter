package com.mprog.spring;

import com.mprog.spring.config.ApplicationConfiguration;
import com.mprog.spring.database.pool.ConnectionPool;
import com.mprog.spring.database.repository.CompanyRepository;
import com.mprog.spring.database.repository.CrudRepository;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApplicationRunner {

    public static void main(String[] args) {
        try (var context = new AnnotationConfigApplicationContext(ApplicationConfiguration.class)) {
            System.out.println(context.getBean("pool1", ConnectionPool.class));
            var companyRepository = context.getBean("companyRepository", CrudRepository.class);
            System.out.println(companyRepository.findById(1));
        }
    }
}

