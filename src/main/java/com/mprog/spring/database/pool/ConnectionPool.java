package com.mprog.spring.database.pool;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component("pool1")
@RequiredArgsConstructor
public class ConnectionPool {

    @Value("${db.username}")
    private final String username;
    @Value("${db.pool.size}")
    private final Integer poolSize;
//    private List<Object> args;
//    private Map<String, Object> properties;

    @PostConstruct
    private void init() {
        System.out.println("Connection pool initialization");
    }

    @PreDestroy
    private void destroy() {
        System.out.println("Connection pool is cleaning");
    }
}
