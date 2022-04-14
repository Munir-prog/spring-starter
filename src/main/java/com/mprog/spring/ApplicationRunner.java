package com.mprog.spring;

import com.mprog.spring.database.pool.ConnectionPool;
import com.mprog.spring.database.repository.CompanyRepository;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApplicationRunner {

    public static void main(String[] args) {
        var context = new ClassPathXmlApplicationContext("application.xml");
        System.out.println(context.getBean("pool1", ConnectionPool.class));
        var companyRepository = context.getBean("companyRepository", CompanyRepository.class);
        System.out.println(companyRepository);
    }
}

