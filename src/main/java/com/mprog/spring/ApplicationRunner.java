package com.mprog.spring;

import com.mprog.spring.database.pool.ConnectionPool;
import com.mprog.spring.database.repository.CompanyRepository;
import com.mprog.spring.database.repository.UserRepository;
import com.mprog.spring.ioc.Container;
import com.mprog.spring.service.UserService;

public class ApplicationRunner {

    public static void main(String[] args) {
        Container container = new Container();

        //        ConnectionPool connectionPool = new ConnectionPool();
//        UserRepository userRepository = new UserRepository(connectionPool);
//        CompanyRepository companyRepository = new CompanyRepository(connectionPool);
//        UserService userService = new UserService(userRepository, companyRepository);

//        ConnectionPool connectionPool = container.get(ConnectionPool.class);
//        UserRepository userRepository = container.get(UserRepository.class);
//        CompanyRepository companyRepository = container.get(CompanyRepository.class);
        UserService userService = container.get(UserService.class);


    }
}
